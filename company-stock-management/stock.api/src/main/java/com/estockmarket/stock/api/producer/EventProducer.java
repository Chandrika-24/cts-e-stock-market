package com.estockmarket.stock.api.producer;

import com.estockmarket.company.core.events.AddStockEvent;

public interface EventProducer {
    void produce(String topic, AddStockEvent event);
}