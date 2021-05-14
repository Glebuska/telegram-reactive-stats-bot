package com.intern.bot.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ClientProperties {

  @Value("${uri}")
  private String uri;
}
