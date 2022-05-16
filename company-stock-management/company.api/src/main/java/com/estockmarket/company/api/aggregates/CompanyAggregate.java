package com.estockmarket.company.api.aggregates;

import com.estockmarket.company.api.commands.RegisterCompanyCommand;
import com.estockmarket.company.api.commands.RemoveCompanyCommand;
import com.estockmarket.company.api.commands.UpdateCompanyCommand;
import com.estockmarket.company.core.events.CompanyRegisteredEvent;
import com.estockmarket.company.core.events.CompanyRemovedEvent;
import com.estockmarket.company.core.events.CompanyUpdatedEvent;
import com.estockmarket.company.core.models.Company;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.Valid;

@Aggregate
public class CompanyAggregate {
    @AggregateIdentifier
    private String id;
    private Company company;

    public CompanyAggregate() {
    }

    @CommandHandler
    public CompanyAggregate(@Valid RegisterCompanyCommand command) {
        var newCompany = command.getCompany();
        newCompany.setId(command.getId());
        var event = CompanyRegisteredEvent.builder()
                .id(newCompany.getCompanycode())
                .company(newCompany)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateCompanyCommand command) {
        var updatedCompany = command.getCompany();
        var event = CompanyUpdatedEvent.builder()
                .id(command.getId())
                .company(updatedCompany)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveCompanyCommand command) {
        var event = new CompanyRemovedEvent();
        event.setCompanycode(command.getCompanycode());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CompanyRegisteredEvent event) {
        this.id = event.getCompany().getCompanycode();
        this.company = event.getCompany();
    }

    @EventSourcingHandler
    public void on(CompanyUpdatedEvent event) {
        this.company = event.getCompany();
    }

    @EventSourcingHandler
    public void on(CompanyRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
