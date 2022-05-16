package com.estockmarket.stock.api.handlers;

import com.estockmarket.company.core.events.AddStockEvent;
import com.estockmarket.stock.api.producer.EventProducer;
import com.estockmarket.stock.api.repositories.StockRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("company-group")
public class StockEventHandlerImpl implements StockEventHandler{

    @Autowired
    private EventProducer eventProducer;

    private final StockRepository stockRepository;

    @Autowired
    public StockEventHandlerImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @EventHandler
    @Override
    public void on(AddStockEvent event) {
        stockRepository.save(event.getStock());
        eventProducer.produce(event.getClass().getSimpleName(), event);
    }
}
