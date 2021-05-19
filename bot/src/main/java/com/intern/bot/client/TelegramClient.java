package com.intern.bot.client;

import com.intern.bot.config.properties.BotCommandProperties;
import com.intern.bot.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TelegramClient {

  private final TelegramBotService telegramBotService;
  private final BotCommandProperties botCommandProperties;

  public Mono<String> messageHandler(Message message) {
    Mono<String> queryResult = Mono.empty();
    String inputText = message.getText();
    if (inputText.equals(botCommandProperties.getTopByMessageAmount())) {
      queryResult = telegramBotService.getTopByMessageAmount(message.getChatId());
    } else if (inputText.equals(botCommandProperties.getInfo())) {
      queryResult =
          telegramBotService.getInfo(message.getFrom().getUserName(), message.getChatId());
    } else {
      telegramBotService.saveMessage(message);
    }

    return queryResult;
  }
}
