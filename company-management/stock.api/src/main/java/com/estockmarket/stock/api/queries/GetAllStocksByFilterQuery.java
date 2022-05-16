package com.estockmarket.stock.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GetAllStocksByFilterQuery {
    private String companycode;
    private Date startDate;
    private Date endDate;
}
