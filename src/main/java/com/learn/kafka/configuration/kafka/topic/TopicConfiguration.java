package com.learn.kafka.configuration.kafka.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfiguration {
    @Bean
    public NewTopic video() {
        return new NewTopic("video", 1, (short)1);
    }
}
