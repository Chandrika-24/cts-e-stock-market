package com.estockmarket.stock.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllStocksByFilterQuery {
    private String companycode;
    private String startDate;
    private String endDate;
}
