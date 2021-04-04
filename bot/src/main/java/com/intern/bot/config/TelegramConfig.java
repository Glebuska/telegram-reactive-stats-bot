package com.intern.bot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TelegramConfig {

  private final TelegramBot bot;

  @Bean
  public TelegramBotsApi registerBot() {
    TelegramBotsApi botsApi = null;
    try {
      botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(bot);
    } catch (TelegramApiException e) {
      log.error("Could not initialize telegram bot API.", e);
    }

    return botsApi;
  }

}
