package com.publicservice.v1.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-config")
@Getter
@Setter
public class ApplicationPropertiesConfiguration {

  private int initialTime;
  private int extraTime;

}
