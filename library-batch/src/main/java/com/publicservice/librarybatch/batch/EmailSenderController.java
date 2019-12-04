package com.publicservice.librarybatch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSenderController {
  private JobLauncher jobLauncher;
  private Job job;

//  public EmailSenderController(JobLauncher jobLauncher, Job job) {
//    this.jobLauncher = jobLauncher;
//    this.job = job;
//  }
//
//  @RequestMapping("/sendEmailDelay")
//  public BatchStatus send()  throws Exception{
//    Map<String, JobParameter> parameterMap = new HashMap<>();
//    parameterMap.put("time",new JobParameter(System.currentTimeMillis()));
//    JobParameters jobParameters = new JobParameters(parameterMap);
//    JobExecution jobExecution = jobLauncher.run(job,jobParameters);
//    return jobExecution.getStatus();
//  }
}
