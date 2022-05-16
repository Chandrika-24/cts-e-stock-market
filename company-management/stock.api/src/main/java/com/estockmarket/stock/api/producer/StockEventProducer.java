package com.estockmarket.stock.api.producer;

import com.estockmarket.company.core.events.AddStockEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StockEventProducer implements EventProducer{
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, AddStockEvent event) {

        this.kafkaTemplate.send(topic, event);
    }
}
