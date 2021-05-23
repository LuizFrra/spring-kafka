package com.learn.kafka.controller;

import com.learn.kafka.DTOs.VideoDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @PostMapping
    public ResponseEntity<VideoDTO> publishVideo(@RequestBody VideoDTO videoDTO) {
        kafkaTemplate.send(new ProducerRecord<>("video", videoDTO));
        return new ResponseEntity<>(videoDTO, HttpStatus.CREATED);
    }
}
