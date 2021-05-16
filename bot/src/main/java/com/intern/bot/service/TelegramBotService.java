package com.intern.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.bot.config.properties.EndpointProperties;
import com.intern.bot.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TelegramBotService {

  private final WebClient webClient;
  private final ObjectMapper objectMapper;
  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final EndpointProperties endpointProperties;
  private final KafkaProperties kafkaProperties;

  public Mono<String> getTopByMessageAmount(Long chatId) {
    return webClient
        .get()
        .uri(endpointProperties.getTopByMessageAmount(), chatId)
        .retrieve()
        .bodyToMono(String.class);
  }

  public Mono<String> getInfo(String username, Long chatId) {
    return webClient
        .get()
        .uri(endpointProperties.getInfo(), username, chatId)
        .retrieve()
        .bodyToMono(String.class);
  }

  public void saveMessage(Message inputMessage) {
    kafkaTemplate.send(kafkaProperties.getTopic(), writeValueAsString(inputMessage));
  }

  private String writeValueAsString(Message message) {
    try {
      return objectMapper.writeValueAsString(message);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Writing value to JSON failed: " + message.toString());
    }
  }
}
