package com.intern.api.client;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataBaseClient {

  final MongoCollection<Document> collection;

  public Document deleteMessage(Long messageId) {
    Document doc = collection.find(eq("message_id", messageId)).first();
    collection.deleteOne(doc);
    log.debug("Document: {} delete successfully", doc.toJson());
    return doc;
  }

  public ArrayList<Document> getMessages() {
    return collection.find().into(new ArrayList<>());
  }

  public ArrayList<Document> getMessagesInChat(Long chatId) {
    return collection.find(eq("chat.id", chatId)).into(new ArrayList<>());
  }
}
