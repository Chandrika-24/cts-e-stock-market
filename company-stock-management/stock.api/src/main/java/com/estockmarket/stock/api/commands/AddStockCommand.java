package com.estockmarket.stock.api.commands;

import com.estockmarket.company.core.models.Stock;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddStockCommand {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "stock cannot be null")
    @Valid
    private Stock stock;
}
