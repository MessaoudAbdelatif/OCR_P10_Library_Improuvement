package com.publicservice.zuulserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicservice.zuulserver.configuration.ApplicationPropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZuulServerApplication.class, args);
  }

  @Bean
  BCryptPasswordEncoder getBCPE() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  ObjectMapper getOM() {
    return new ObjectMapper();
  }
  @Bean
  ApplicationPropertiesConfiguration getAPC() {
    return new ApplicationPropertiesConfiguration();
  }
}