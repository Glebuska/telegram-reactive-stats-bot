package com.intern.api.client;

import static com.mongodb.client.model.Accumulators.first;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    Document doc = collection.findOneAndDelete(eq("message_id", messageId));
    log.debug("Document: {} delete successfully", doc.toJson());
    return doc;
  }

  public List<Document> getMessages() {
    return collection.find().into(new ArrayList<>());
  }

  public List<Document> getMessagesByChatId(Long chatId) {
    return collection.find(eq("chat.id", chatId)).into(new ArrayList<>());
  }

  public List<Document> getPreviousMessages(Long userId) {
    return collection.find(eq("from.id", userId)).limit(10).into(new ArrayList<>());
  }

  public Document getUserInfo(String username) {
    return collection.find(eq("from.username", username)).first().get("from", Document.class);
  }

  public List<Document> getTopUsers(int N, Optional<Integer> chatId) {
    if (chatId.isEmpty()) {

      return collection
          .aggregate(
              Arrays.asList(
                  group("$from.username", first("username", "$from.username"), sum("count", 1)),
                  project(fields(include("username", "count"), excludeId())),
                  sort(Sorts.descending("count")),
                  limit(N)))
          .into(new ArrayList<>());
    }
    return collection
        .aggregate(
            Arrays.asList(
                match(eq("chat.id", chatId.get())),
                group("$from.username", first("username", "$from.username"), sum("count", 1)),
                project(fields(include("username", "count"), excludeId())),
                sort(Sorts.descending("count")),
                limit(N)))
        .into(new ArrayList<>());
  }
}
