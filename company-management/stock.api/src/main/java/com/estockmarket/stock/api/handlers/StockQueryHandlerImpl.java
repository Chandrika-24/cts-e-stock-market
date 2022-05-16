package com.estockmarket.stock.api.handlers;

import com.estockmarket.stock.api.dto.StockLookupResponse;
import com.estockmarket.company.core.models.Stock;
import com.estockmarket.stock.api.queries.GetAllStocksByCompanyCodeQuery;
import com.estockmarket.stock.api.repositories.StockRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockQueryHandlerImpl implements StockQueryHandler{
    private final StockRepository stockRepository;

    @Autowired
    public StockQueryHandlerImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @QueryHandler
    @Override
    public StockLookupResponse on(GetAllStocksByCompanyCodeQuery query) {
        var stocks = stockRepository.findAllByCompanycode(query.getCompanycode());
        return new StockLookupResponse(stocks);
    }
}
