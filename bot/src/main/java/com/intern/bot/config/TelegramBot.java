package com.intern.bot.config;

import com.intern.bot.client.TelegramClient;
import com.intern.bot.config.properties.BotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramBot extends TelegramLongPollingBot {

  private final BotProperties botProperties;

  private final TelegramClient telegramClient;

  @Override
  public void onUpdateReceived(Update update) {
    // We check if the update has a message and the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {
      Message inputMessage = update.getMessage();
      Mono<String> result = telegramClient.messageHandler(inputMessage);
      if (result != null) {
        result.subscribe(
            text -> {
              log.info(text);
              SendMessage sendMessage = new SendMessage();
              sendMessage.setChatId(inputMessage.getChatId().toString());
              sendMessage.setText(text);
              try {
                execute(sendMessage);
              } catch (TelegramApiException e) {
                // just igonre, because spring will log it, right?
              }
            });
      }
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
