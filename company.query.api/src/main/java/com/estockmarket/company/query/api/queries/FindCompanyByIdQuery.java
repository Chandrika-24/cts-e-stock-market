package com.estockmarket.company.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCompanyByIdQuery {
    private String id;
}
