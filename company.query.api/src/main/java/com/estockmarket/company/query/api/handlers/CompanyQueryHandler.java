package com.estockmarket.company.query.api.handlers;

import com.estockmarket.company.query.api.dto.CompanyLookupResponse;
import com.estockmarket.company.query.api.queries.FindAllCompaniesQuery;
import com.estockmarket.company.query.api.queries.FindCompanyByIdQuery;
import com.estockmarket.company.query.api.queries.SearchCompanyQuery;

public interface CompanyQueryHandler {
    CompanyLookupResponse getUserById(FindCompanyByIdQuery query);
    CompanyLookupResponse searchCompanies(SearchCompanyQuery query);
    CompanyLookupResponse getAllCompanies(FindAllCompaniesQuery query);
}
