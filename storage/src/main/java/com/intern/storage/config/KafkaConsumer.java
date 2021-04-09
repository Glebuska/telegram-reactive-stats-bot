package com.intern.storage.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Slf4j
@Configuration
public class KafkaConsumer {

  private final String topicName = "topic2";

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name(topicName)
        .partitions(2)
        .replicas(1)
        .build();
  }

  @KafkaListener(id = "myId", topics = topicName)
  public void listen(String in) {
    log.info(in);
  }

}
