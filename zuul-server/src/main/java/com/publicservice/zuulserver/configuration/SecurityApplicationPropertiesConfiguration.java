package com.publicservice.zuulserver.configuration;


import lombok.Getter;
import lombok.Setter;

//@Component
//@ConfigurationProperties("my-config")
@Setter
@Getter
public class SecurityApplicationPropertiesConfiguration {

  private String jWTHeaderName;
  private String secret;
  private Long expiration;
  private String headerPrefix;
}