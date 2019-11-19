package com.publicservice.zuulserver.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-security-config")
@Getter
@Setter
public class ApplicationPropertiesConfiguration {

  private String JWT_HEADER_NAME;
  private String SECRET;
  private int EXPIRATION;
  private String HEADER_PREFIX;
}
