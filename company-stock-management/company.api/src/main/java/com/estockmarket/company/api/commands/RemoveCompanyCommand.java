package com.estockmarket.company.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class RemoveCompanyCommand {
    @TargetAggregateIdentifier
    private String companycode;
}
