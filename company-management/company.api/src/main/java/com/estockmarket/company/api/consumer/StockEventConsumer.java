package com.estockmarket.company.api.consumer;

import com.estockmarket.company.api.handlers.CompanyEventHandler;
import com.estockmarket.company.api.repositories.CompanyRepository;
import com.estockmarket.company.core.events.AddStockEvent;
import com.estockmarket.company.core.events.CompanyUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class StockEventConsumer implements EventConsumer{

    @Autowired
    private CompanyEventHandler companyEventHandler;

    @Autowired
    private CompanyRepository companyRepository;

    @KafkaListener(topics = "AddStockEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload AddStockEvent event, Acknowledgment ack) {
        var company = companyRepository.findByCompanycode(event.getStock().getCompanycode());
        if (company != null) {
            company.setStock(event.getStock());
            this.companyEventHandler.on(CompanyUpdatedEvent.builder()
                    .id(event.getId())
                    .company(company)
                    .build());
            ack.acknowledge();
        }
    }
}
