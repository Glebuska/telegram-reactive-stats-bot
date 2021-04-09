package com.intern.bot.config;

import com.intern.bot.config.properties.BotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramBot extends TelegramLongPollingBot {

  private final BotProperties botProperties;
  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void onUpdateReceived(Update update) {
    // We check if the update has a message and the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {
      String inputText = update.getMessage().getText();
      kafkaTemplate.send("topic2", inputText);
    }
  }

  @Override
  public String getBotUsername() {
    return botProperties.getUsername();
  }

  @Override
  public String getBotToken() {
    return botProperties.getToken();
  }

}
