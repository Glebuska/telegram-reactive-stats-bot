package com.intern.bot.service;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class MyBot extends TelegramLongPollingBot {

  private static final Map<String, String> getenv = System.getenv();

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
    return "GridInternBot";
  }

  @Override
  public String getBotToken() {
    //TODO HIDDEN KEEP TOKEN?
    return "1716055129:AAHWA-utqyyw6DVNYB_TGAHDnU2jPNHLV7o";
  }

}

