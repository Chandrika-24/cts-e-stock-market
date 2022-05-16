package com.estockmarket.stock.api.handlers;

import com.estockmarket.stock.api.dto.StockLookupResponse;
import com.estockmarket.stock.api.queries.GetAllStocksByCompanyCodeQuery;

public interface StockQueryHandler {
    StockLookupResponse on(GetAllStocksByCompanyCodeQuery query);
}
