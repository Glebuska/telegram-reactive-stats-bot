package com.intern.api.controller;

import com.intern.api.client.DataBaseClient;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mongo")
public class MongoController {

  final DataBaseClient mongoDataBaseClient;

  @DeleteMapping("/{id}")
  public @ResponseBody Document deleteById(@PathVariable Long id) {
    return mongoDataBaseClient.deleteMessage(id);
  }

  @GetMapping
  public @ResponseBody ArrayList<Document> getAllMessage() {
    return mongoDataBaseClient.getMessages();
  }

  @GetMapping("/{id}")
  public @ResponseBody ArrayList<Document> getMessagesInChat(@PathVariable Long id) {
    return mongoDataBaseClient.getMessagesInChat(id);
  }
}
