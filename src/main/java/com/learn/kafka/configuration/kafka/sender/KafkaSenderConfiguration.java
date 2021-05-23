package com.learn.kafka.configuration.kafka.sender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import reactor.core.Disposable;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaSenderConfiguration<K, V> {
    @Bean
    public KafkaSender<K, V> createKafkaSender(ProducerFactory<K, V> producerFactory) {
        SenderOptions<K, V> senderOptions = SenderOptions.<K, V>
                create(producerFactory.getConfigurationProperties())
/*                Can use This to set the number os threads, default is an single thread
                .scheduler(Schedulers.newParallel("producer-thread", 10))*/
                .maxInFlight(1024);
        return KafkaSender.create(senderOptions) ;
    }
}
