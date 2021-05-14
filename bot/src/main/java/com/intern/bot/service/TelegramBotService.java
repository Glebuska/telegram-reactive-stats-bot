package com.intern.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  public Mono<String> getTopByMessageAmount(Long chatId) {
    return webClient
        .get()
        .uri("/mongo/top/5?chatId={chatId}", chatId)
        .retrieve()
        .bodyToMono(String.class);
  }

  public Mono<String> getInfo(String username, Long chatId) {
    return webClient
        .get()
        .uri("/mongo/user/{username}?chatId={chatId}", username, chatId)
        .retrieve()
        .bodyToMono(String.class);
  }

  public void saveMessage(Message inputMessage) {
    kafkaTemplate.send("mytopic", writeValueAsString(inputMessage));
  }

  private String writeValueAsString(Message message) {
    try {
      return objectMapper.writeValueAsString(message);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Writing value to JSON failed: " + message.toString());
    }
  }
}
