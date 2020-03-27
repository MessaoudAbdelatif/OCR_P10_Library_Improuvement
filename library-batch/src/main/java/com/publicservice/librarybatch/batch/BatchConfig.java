package com.publicservice.librarybatch.batch;

import com.publicservice.librarybatch.email.ReminderController;
import com.publicservice.librarybatch.model.DelayBorrowUser;
import com.publicservice.librarybatch.proxy.MSLibraryApiProxy;
import java.util.List;
import javax.xml.bind.ValidationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Configuration
//@EnableBatchProcessing
@RestController
public class BatchConfig {

  private MSLibraryApiProxy msLibraryApiProxy;
  private ReminderController reminderController;

  public BatchConfig(MSLibraryApiProxy msLibraryApiProxy,
      ReminderController reminderController) {
    this.msLibraryApiProxy = msLibraryApiProxy;
    this.reminderController = reminderController;
  }

  public void sendEmailAuto(DelayBorrowUser delayBorrowUser)
      throws ValidationException {

    reminderController.sendReminder(delayBorrowUser);
  }

  public void sendBookingEmailAuto(DelayBorrowUser delayBorrowUser) {

    reminderController.sendBookingReminder(delayBorrowUser);
  }

  @Scheduled(cron = "0 30 7 * * ?")
  @GetMapping("/send")
  public void sender() {
    List<DelayBorrowUser> dbusers = msLibraryApiProxy.overTimeLimite();
    dbusers
        .forEach(delayBorrowUser -> {
          try {
            sendEmailAuto(delayBorrowUser);
          } catch (ValidationException e) {
            e.printStackTrace();
          }
        });
  }

  @Scheduled(cron = "0/30 * * * * ?")
  @GetMapping("/sendBookingReminder")
  public void senderBooking() {
    List<DelayBorrowUser> delayBorrowUsers = msLibraryApiProxy.notifyBookedUsed();
    delayBorrowUsers.forEach(this::sendBookingEmailAuto);
  }

//
//  private EmailConfig emailConfig;
//  private ReminderController reminderController;
//  private MSLibraryApiProxy msLibraryApiProxy;
//  private JobBuilderFactory jobBuilderFactory;
//  private StepBuilderFactory stepBuilderFactory;
//  private ListItemReader readerAdapter;
//  private ItemWriterAdapter writerAdapter;
//
//
//  public BatchConfig(EmailConfig emailConfig,
//      ReminderController reminderController,
//      JobBuilderFactory jobBuilderFactory,
//      StepBuilderFactory stepBuilderFactory,
//      ListItemReader readerAdapter,
//      ItemWriterAdapter writerAdapter) {
//    this.emailConfig = emailConfig;
//    this.reminderController = reminderController;
//    this.jobBuilderFactory = jobBuilderFactory;
//    this.stepBuilderFactory = stepBuilderFactory;
//    this.readerAdapter = readerAdapter;
//    this.writerAdapter = writerAdapter;
//  }
//
//  @Bean
//  public Job emailSenderJob() {
//    return jobBuilderFactory.get("email-sender-job")
//        .start(step1())
//        .build();
//  }
//
//  @Bean
//  public Step step1() {
//    return this.stepBuilderFactory.get("step1")
//        .<DelayBorrowUser, DelayBorrowUser>chunk(100)
//        .reader(readerAdapter)
//        .writer(writerAdapter)
//        .build();
//  }
//
//  @Bean
//  public ListItemReader<DelayBorrowUser> reader() {
//    return new ListItemReader(getListOfDelay());
//  }
////  public ListItemReader itemReader() {
////    ItemReaderAdapter readerAdapter = new ItemReaderAdapter();
////    readerAdapter.setTargetObject(msLibraryApiProxy);
////    readerAdapter.setTargetMethod("overTimeLimite");
////    return readerAdapter;
////  }
//
//  @Bean
//  public ItemWriterAdapter itemWriter() {
//    ItemWriterAdapter writer = new ItemWriterAdapter();
//    writer.setTargetObject(reminderController);
//    writer.setTargetMethod("sendReminder");
//    return writer;
//  }
//
//  public List<DelayBorrowUser> getListOfDelay() {
//    List<DelayBorrowUser> usersDelay = msLibraryApiProxy.overTimeLimite();
//    return usersDelay;
//  }
//
////  @Bean
////  public ItemProcessor<DelayBorrowUser, DelayBorrowUser> itemProcessor() {
////    return new ItemProcessor<DelayBorrowUser, DelayBorrowUser>() {
////      @Override
////      public DelayBorrowUser process(DelayBorrowUser DelayBorrowUser) throws Exception {
////        DelayBorrowUser delayBorrowUser = new DelayBorrowUser();
////        return delayBorrowUser;
////      }
////    };
////  }

}
