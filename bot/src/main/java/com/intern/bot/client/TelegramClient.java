package com.intern.bot.client;

import com.intern.bot.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TelegramClient {

  final TelegramBotService telegramBotService;

  public Mono<String> messageHandler(Message message) {
    Mono<String> queryResult = null;
    switch (message.getText()) {
      case "/topByMessageAmount":
        queryResult = telegramBotService.getTopByMessageAmount(message.getChatId());
        break;
      case "/info":
        queryResult =
            telegramBotService.getInfo(message.getFrom().getUserName(), message.getChatId());
      default:
        telegramBotService.saveMessage(message);
    }
    return queryResult;
  }
}
