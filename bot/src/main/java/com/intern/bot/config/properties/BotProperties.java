package com.intern.bot.config.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Configuration
public class BotProperties extends TelegramLongPollingBot {

  @Value("${bot.token}")
  private String token;
  @Value("${bot.username}")
  private String username;

  @Override
  public void onUpdateReceived(Update update) {
    // We check if the update has a message and the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {
      String inputText = update.getMessage().getText();
      log.info(inputText);
    }
  }

  @Override
  public String getBotUsername() {
    return username;
  }

  @Override
  public String getBotToken() {
    return token;
  }

}

