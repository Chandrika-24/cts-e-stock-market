package com.estockmarket.company.query.api.handlers;

import com.estockmarket.company.core.models.Company;
import com.estockmarket.company.query.api.dto.CompanyLookupResponse;
import com.estockmarket.company.query.api.queries.FindAllCompaniesQuery;
import com.estockmarket.company.query.api.queries.FindCompanyByIdQuery;
import com.estockmarket.company.query.api.queries.SearchCompanyQuery;
import com.estockmarket.company.query.api.repositories.CompanyRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyQueryHandlerImpl implements  CompanyQueryHandler{

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyQueryHandlerImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @QueryHandler
    @Override
    public CompanyLookupResponse getUserById(FindCompanyByIdQuery query) {
        var company = companyRepository.findById(query.getId());
        return company.isPresent() ? new CompanyLookupResponse(company.get()) : null;
    }

    @QueryHandler
    @Override
    public CompanyLookupResponse searchCompanies(SearchCompanyQuery query) {
        var companies = new ArrayList<Company>(companyRepository.findByFilterRegex(query.getFilter()));
        return new CompanyLookupResponse(companies);
    }

    @QueryHandler
    @Override
    public CompanyLookupResponse getAllCompanies(FindAllCompaniesQuery query) {
        var companies = new ArrayList<>(companyRepository.findAll());
        return new CompanyLookupResponse(companies);
    }
}
