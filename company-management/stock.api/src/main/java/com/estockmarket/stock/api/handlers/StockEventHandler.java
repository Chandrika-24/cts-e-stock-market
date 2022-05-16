package com.estockmarket.stock.api.handlers;

import com.estockmarket.company.core.events.AddStockEvent;

public interface StockEventHandler {

    void on(AddStockEvent event);
}
