package com.estockmarket.stock.api.dto;

import com.estockmarket.company.core.models.Stock;

import java.util.List;

public class StockLookupResponse extends BaseResponse{

    private List<Stock> stocks;

    public StockLookupResponse(String message) {
        super(message);
    }

    public StockLookupResponse(List<Stock> stocks) {
        super(null);
        this.stocks = stocks;
    }

    public List<Stock> getStocks() {
        return this.stocks;
    }

    public void setStocks() {
        this.stocks = stocks;
    }
}
