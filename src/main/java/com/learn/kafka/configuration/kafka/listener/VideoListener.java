package com.learn.kafka.configuration.kafka.listener;

import com.learn.kafka.DTOs.VideoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class VideoListener {

    private final Logger log = LoggerFactory.getLogger(VideoListener.class);

    private final String TOPIC = "video";

    private final String GROUP_ID = "videoListener";

    @KafkaListener(topics = {TOPIC}, groupId = GROUP_ID)
    public void videoListener(VideoDTO videoDTO) {
        log.info("Receiving Message from Topic {}", TOPIC);
        log.info(String.valueOf(videoDTO));
    }
}
