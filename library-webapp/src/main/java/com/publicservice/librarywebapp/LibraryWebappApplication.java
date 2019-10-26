package com.publicservice.librarywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.publicservice")
public class LibraryWebappApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryWebappApplication.class, args);

  }
}
