package com.intern.bot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "endpoint")
public class EndpointProperties {

  private String topByMessageAmount;
  private String info;
}
