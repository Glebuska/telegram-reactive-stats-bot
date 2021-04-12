package com.intern.storage.config;

import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumer {

  private final String topicName = "topic2";
  final MongoCollection<Document> collection;

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name(topicName).partitions(2).replicas(1).build();
  }

  @KafkaListener(id = "myId", topics = topicName)
  public void listen(String in) {
    Document document = Document.parse(in);
    log.info(in);
    collection.insertOne(document);
  }
}
