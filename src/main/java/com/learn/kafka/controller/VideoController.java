package com.learn.kafka.controller;

import com.learn.kafka.DTOs.VideoDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    private Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Autowired
    private KafkaSender<String, Object> kafkaSender;

    @PostMapping("/reactive")
    public Mono<ResponseEntity<VideoDTO>> publishVideoReactive(@RequestBody VideoDTO videoDTO) {
        return kafkaSender.createOutbound()
                .send(Mono.just(new ProducerRecord<>("video", videoDTO)))
                .then()
                .doOnError(Throwable::printStackTrace)
                .doOnSuccess(s -> logger.info("Publishing {}", videoDTO))
                .thenReturn(new ResponseEntity<>(videoDTO, HttpStatus.CREATED));
    }

    @PostMapping
    public ResponseEntity<VideoDTO> publishVideo(@RequestBody VideoDTO videoDTO) {
        kafkaTemplate.send("video", videoDTO);
        return new ResponseEntity<>(videoDTO, HttpStatus.CREATED);
    }
}
