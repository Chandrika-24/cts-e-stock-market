package com.estockmarket.stock.api.handlers;

import com.estockmarket.stock.api.dto.StockLookupResponse;
import com.estockmarket.stock.api.queries.GetAllStocksByCompanyCodeQuery;
import com.estockmarket.stock.api.queries.GetAllStocksByFilterQuery;

public interface StockQueryHandler {
    StockLookupResponse on(GetAllStocksByCompanyCodeQuery query);
    StockLookupResponse on(GetAllStocksByFilterQuery query);
}
