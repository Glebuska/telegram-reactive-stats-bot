package com.intern.bot.config;

import com.intern.bot.config.properties.ClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class ClientConfig {
  final ClientProperties clientProperties;

  @Bean
  public WebClient webClient() {
    return WebClient.create(clientProperties.getAddress());
  }
}
