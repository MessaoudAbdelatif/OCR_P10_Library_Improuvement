package com.publicservice.librarybatch.email;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-email-config")
@Getter
@Setter
public class EmailConfig {

  private String host;
  private int port;
  private String username;
  private String password;
  private String feedback;
}
