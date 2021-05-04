package com.intern.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "mongodb")
public class MongoProperties {
  private String database;
  private String collection;
  private String uri;
}
