package com.publicservice.librarybatch.email;


import com.publicservice.librarybatch.model.DelayBorrowUser;
import javax.xml.bind.ValidationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
  public void sendReminder(@RequestBody DelayBorrowUser delayBorrowUser
//      ,BindingResult bindingResult
  ) throws ValidationException {
//    if (bindingResult.hasErrors()) {
//      throw new ValidationException("Feedback is not valid");
//    }

    // Create a mail sender
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(this.emailCfg.getHost());
    mailSender.setPort(this.emailCfg.getPort());
    mailSender.setUsername(this.emailCfg.getUsername());
    mailSender.setPassword(this.emailCfg.getPassword());

    // Create an email instance
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("No-Replay@PublicServiceLibrary.lib");
    mailMessage.setTo(delayBorrowUser.getEmail());
    mailMessage.setSubject("Hello " + delayBorrowUser.getFirstname()
        + " we just remind you that you have to bring back " + delayBorrowUser.getName());
    mailMessage.setText(emailCfg.getFeedback());

    // Send mail
    mailSender.send(mailMessage);
  }


  @PostMapping
  public void sendBookingReminder(@RequestBody DelayBorrowUser delayBorrowUser)
//      ,BindingResult bindingResult
  {
//    if (bindingResult.hasErrors()) {
//      throw new ValidationException("Feedback is not valid");
//    }

    // Create a mail sender
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(this.emailCfg.getHost());
    mailSender.setPort(this.emailCfg.getPort());
    mailSender.setUsername(this.emailCfg.getUsername());
    mailSender.setPassword(this.emailCfg.getPassword());

    // Create an email instance
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("No-Replay@PublicServiceLibrary.lib");
    mailMessage.setTo(delayBorrowUser.getEmail());
    mailMessage.setSubject("Hello " + delayBorrowUser.getFirstname()
        + " Your reserved " + delayBorrowUser.getName() + " book is available now !");
    mailMessage.setText(emailCfg.getFeedbackBooking());

    // Send mail
    mailSender.send(mailMessage);
  }
}
