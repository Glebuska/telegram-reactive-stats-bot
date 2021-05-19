package com.intern.api.controller;

import com.intern.api.client.DataBaseClient;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mongo")
public class MessageController {

  final DataBaseClient mongoDataBaseClient;

  @DeleteMapping
  public @ResponseBody Document deleteMessageById(@RequestParam("id") Long id) {
    return mongoDataBaseClient.deleteMessage(id);
  }

  @GetMapping
  public @ResponseBody List<Document> getAllMessage() {
    return mongoDataBaseClient.getMessages();
  }

  @GetMapping("/{id}")
  public @ResponseBody List<Document> getMessagesByChatId(@PathVariable Long id) {
    return mongoDataBaseClient.getMessagesByChatId(id);
  }

  @GetMapping("/top/{size}")
  public @ResponseBody List<Document> getTopUsersByMessageAmount(
      @PathVariable int size,
      @RequestParam(value = "chatId", required = false) Optional<Integer> chatId) {
    return mongoDataBaseClient.getTopUsersByMessageAmount(size, chatId);
  }

  @GetMapping("/oldMessages/{userId}")
  public @ResponseBody List<Document> getPreviousMessages(
      @PathVariable Long userId, @RequestParam(value = "limit", defaultValue = "10") int limit) {
    return mongoDataBaseClient.getPreviousMessages(userId, limit);
  }

  @GetMapping("/user/{userName}")
  public @ResponseBody Document getUserInfo(
      @PathVariable String userName,
      @RequestParam(value = "chatId", required = false) Optional<Integer> chatId) {
    return mongoDataBaseClient.getUserInfo(userName, chatId);
  }
}
