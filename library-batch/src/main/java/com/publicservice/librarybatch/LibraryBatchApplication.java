package com.publicservice.librarybatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Configuration
@SpringBootApplication
@EnableFeignClients("com.publicservice")
@EnableDiscoveryClient
@EnableScheduling
public class LibraryBatchApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryBatchApplication.class, args);
  }

}
