package com.estockmarket.stock.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllStocksByCompanyCodeQuery {
    private String companycode;
}
