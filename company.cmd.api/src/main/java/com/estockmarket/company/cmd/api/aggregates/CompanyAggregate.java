package com.estockmarket.company.cmd.api.aggregates;

import com.estockmarket.company.cmd.api.commands.RegisterCompanyCommand;
import com.estockmarket.company.cmd.api.commands.RemoveCompanyCommand;
import com.estockmarket.company.cmd.api.commands.UpdateCompanyCommand;
import com.estockmarket.company.core.events.CompanyRegisteredEvent;
import com.estockmarket.company.core.events.CompanyRemovedEvent;
import com.estockmarket.company.core.events.CompanyUpdatedEvent;
import com.estockmarket.company.core.models.Company;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class CompanyAggregate {
    @AggregateIdentifier
    private String id;
    private Company company;

    public CompanyAggregate(){

    }

    @CommandHandler
    public CompanyAggregate(RegisterCompanyCommand command) {
        var newCompany = command.getCompany();
        newCompany.setId(command.getId());
        var event = CompanyRegisteredEvent.builder()
                .id(command.getId())
                .company(newCompany)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public CompanyAggregate(UpdateCompanyCommand command) {
        var updatedCompany = command.getCompany();
        updatedCompany.setId(command.getId());
        var event = CompanyUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .company(updatedCompany)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public CompanyAggregate(RemoveCompanyCommand command) {
        var event = new CompanyRemovedEvent();
        event.setId(command.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CompanyRegisteredEvent event) {
        this.id = event.getId();
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
