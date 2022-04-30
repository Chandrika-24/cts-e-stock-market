package com.estockmarket.company.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class RemoveCompanyCommand {
    @TargetAggregateIdentifier
    private String id;
}
