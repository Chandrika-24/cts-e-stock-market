package com.estockmarket.company.api.consumer;

import com.estockmarket.company.core.events.AddStockEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AddStockEvent event, Acknowledgment ack);
}
