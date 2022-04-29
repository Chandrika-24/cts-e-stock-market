package com.estockmarket.company.core.events;

import com.estockmarket.company.core.models.Company;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyUpdatedEvent {
    private String id;
    private Company company;
}
