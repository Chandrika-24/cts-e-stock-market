package com.estockmarket.company.api.handlers;

import com.estockmarket.company.api.dto.CompanyLookupResponse;
import com.estockmarket.company.api.queries.FindAllCompaniesQuery;
import com.estockmarket.company.api.queries.FindCompanyByIdQuery;
import com.estockmarket.company.api.queries.SearchCompanyQuery;

public interface CompanyQueryHandler {
    CompanyLookupResponse getUserById(FindCompanyByIdQuery query);

    CompanyLookupResponse searchCompany(SearchCompanyQuery query);

    CompanyLookupResponse getAllCompanies(FindAllCompaniesQuery query);
}
