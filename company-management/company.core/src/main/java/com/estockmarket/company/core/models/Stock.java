package com.estockmarket.company.core.models;

import com.google.type.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {
    private String stockprice;
    private DateTime datatime;
}
