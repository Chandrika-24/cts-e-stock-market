package com.estockmarket.company.api.dto;

import com.estockmarket.company.core.dto.BaseResponse;
import com.estockmarket.company.core.models.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyLookupResponse extends BaseResponse {
    private List<Company> companies;

    public CompanyLookupResponse(String message) {
        super(message);
    }
    public CompanyLookupResponse(Company company) {
        super(null);
        this.companies = new ArrayList<>();
        this.companies.add(company);
    }

    public CompanyLookupResponse(List<Company> companies) {
        super(null);
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return this.companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}

