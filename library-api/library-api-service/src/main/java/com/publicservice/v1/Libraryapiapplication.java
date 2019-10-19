package com.publicservice.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.publicservice.consumer"})
@EntityScan(basePackages = {"entities"})
@ComponentScan(basePackages = {"com"})
public class Libraryapiapplication {

  public static void main(String[] args) {
    SpringApplication.run(Libraryapiapplication.class, args);
  }

}
