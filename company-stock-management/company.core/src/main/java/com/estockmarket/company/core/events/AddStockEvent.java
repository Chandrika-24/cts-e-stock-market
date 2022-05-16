package com.estockmarket.company.core.events;

import com.estockmarket.company.core.models.Stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddStockEvent {
    private String id;
    private Stock stock;
}
