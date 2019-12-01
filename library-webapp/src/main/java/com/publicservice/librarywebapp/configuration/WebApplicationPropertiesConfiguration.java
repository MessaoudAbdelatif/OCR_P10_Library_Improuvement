package com.publicservice.librarywebapp.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-config")
@Getter
@Setter
public class WebApplicationPropertiesConfiguration {

  private  int sizeDefaultPage;
  private String secret;

}
