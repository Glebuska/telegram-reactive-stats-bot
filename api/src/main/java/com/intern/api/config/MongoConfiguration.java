package com.intern.api.config;

import com.intern.api.config.properties.MongoProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class MongoConfiguration {

  final MongoProperties mongoProp;

  @Bean
  public MongoCollection<Document> getMongoCollection() {
    final MongoClientURI uri =
        new MongoClientURI(
            "mongodb://gmirzazyanov:52ucZPU6EGXkhdw@cluster0-shard-00-00.m0dqp.mongodb.net:27017,cluster0-shard-00-01.m0dqp.mongodb.net:27017,cluster0-shard-00-02.m0dqp.mongodb.net:27017/mydb?ssl=true&replicaSet=atlas-31x190-shard-0&authSource=admin&retryWrites=true&w=majority");
    final MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase(mongoProp.getDatabase());
    return database.getCollection(mongoProp.getCollection());
  }
}
