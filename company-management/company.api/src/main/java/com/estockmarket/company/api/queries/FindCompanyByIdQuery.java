package com.estockmarket.company.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCompanyByIdQuery {
    private String id;
}
