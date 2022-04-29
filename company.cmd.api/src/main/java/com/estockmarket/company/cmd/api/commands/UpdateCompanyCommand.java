package com.estockmarket.company.cmd.api.commands;

import com.estockmarket.company.core.models.Company;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateCompanyCommand {
    @TargetAggregateIdentifier
    private String id;
    private Company company;
}
