package com.intern.storage.config;

import com.intern.storage.config.properties.MongoProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MongoConfiguration {

  final MongoProperties mongoProp;

  @Bean
  public MongoCollection<Document> getMongoCollection() {
    final MongoClientURI uri = new MongoClientURI(mongoProp.getUri());
    final MongoClient mongoClient = new MongoClient(uri);
    final MongoDatabase database = mongoClient.getDatabase(mongoProp.getDatabase());
    return database.getCollection(mongoProp.getCollection());
  }
}
