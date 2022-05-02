package com.estockmarket.company.api.handlers;

import com.estockmarket.company.api.dto.CompanyLookupResponse;
import com.estockmarket.company.api.queries.FindAllCompaniesQuery;
import com.estockmarket.company.api.queries.FindCompanyByIdQuery;
import com.estockmarket.company.api.queries.SearchCompanyQuery;
import com.estockmarket.company.api.repositories.CompanyRepository;
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
    public CompanyLookupResponse searchCompany(SearchCompanyQuery query) {
        var company = companyRepository.findByCompanycode(query.getCompanycode());
        return new CompanyLookupResponse(company);
    }

    @QueryHandler
    @Override
    public CompanyLookupResponse getAllCompanies(FindAllCompaniesQuery query) {
        var companies = new ArrayList<>(companyRepository.findAll());
        return new CompanyLookupResponse(companies);
    }
}
