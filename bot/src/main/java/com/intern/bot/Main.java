package com.intern.bot;

import com.intern.bot.service.MyBot;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
public class Main {

  public static void main(String[] args) {

    try {
      TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(new MyBot());
    } catch (TelegramApiException e) {
      log.error(e.getMessage());
    }
  }
}