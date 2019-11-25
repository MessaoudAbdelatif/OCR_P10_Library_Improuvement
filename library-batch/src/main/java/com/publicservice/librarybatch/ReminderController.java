package com.publicservice.librarybatch;


import javax.xml.bind.ValidationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

  private EmailConfig emailCfg;

  public ReminderController(EmailConfig emailCfg) {
    this.emailCfg = emailCfg;
  }

  @PostMapping
  public void sendFeedback(@RequestBody Reminder reminder,
      BindingResult bindingResult) throws ValidationException {
    if (bindingResult.hasErrors()) {
      throw new ValidationException("Feedback is not valid");
    }

    // Create a mail sender
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(this.emailCfg.getHost());
    mailSender.setPort(this.emailCfg.getPort());
    mailSender.setUsername(this.emailCfg.getUsername());
    mailSender.setPassword(this.emailCfg.getPassword());

    // Create an email instance
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("BorrowService@PublicServiceLibrary.lib");
    mailMessage.setTo(reminder.getEmail());
    mailMessage.setSubject("Hello " + reminder.getName()
        + "we just reminde you that you have to bring back a book(s)");
    mailMessage.setText(reminder.getFeedback());

    // Send mail
    mailSender.send(mailMessage);
  }
}
