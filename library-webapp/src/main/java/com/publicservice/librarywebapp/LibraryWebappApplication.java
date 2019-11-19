package com.publicservice.librarywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableFeignClients("com.publicservice")
@EnableDiscoveryClient
public class LibraryWebappApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryWebappApplication.class, args);

  }

  @Bean
  BCryptPasswordEncoder getBCPE() {
    return new BCryptPasswordEncoder();
  }
}
