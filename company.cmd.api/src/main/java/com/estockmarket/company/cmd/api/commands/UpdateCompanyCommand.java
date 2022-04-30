package com.estockmarket.company.cmd.api.commands;

import com.estockmarket.company.core.models.Company;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateCompanyCommand {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no company details were supplied")
    @Valid
    private Company company;
}
