package com.estockmarket.stock.api.aggregate;

import com.estockmarket.stock.api.commands.AddStockCommand;
import com.estockmarket.company.core.events.AddStockEvent;
import com.estockmarket.company.core.models.Stock;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;

@Aggregate
public class StockAggregate {
    @AggregateIdentifier
    private String id;
    private Stock stock;
    public StockAggregate() {
    }

    @CommandHandler
    public StockAggregate(@Valid AddStockCommand command) {
        var newStock = command.getStock();
        newStock.setId(command.getId());
        var enteredDate = LocalDate.now();
        var enteredTime = LocalTime.now();
        newStock.setEnteredDate(enteredDate);
        newStock.setEnteredTime(enteredTime);
        var event = AddStockEvent.builder()
                .id(command.getId())
                .stock(newStock)
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AddStockEvent event) {
        this.id = event.getId();
        this.stock = event.getStock();
    }


}
