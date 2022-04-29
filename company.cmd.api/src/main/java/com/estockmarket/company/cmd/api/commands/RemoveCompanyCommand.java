package com.estockmarket.company.cmd.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class RemoveCompanyCommand {
    @TargetAggregateIdentifier
    private String id;
}
