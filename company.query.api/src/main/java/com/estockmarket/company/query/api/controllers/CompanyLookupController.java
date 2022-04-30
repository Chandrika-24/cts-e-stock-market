package com.estockmarket.company.query.api.controllers;

import com.estockmarket.company.query.api.dto.CompanyLookupResponse;
import com.estockmarket.company.query.api.queries.FindAllCompaniesQuery;
import com.estockmarket.company.query.api.queries.FindCompanyByIdQuery;
import com.estockmarket.company.query.api.queries.SearchCompanyQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/companyLookup")
public class CompanyLookupController {
    private final QueryGateway queryGateway;

    @Autowired
    public CompanyLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    public ResponseEntity<CompanyLookupResponse> getAllCompanies() {
        try {
            var query = new FindAllCompaniesQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(CompanyLookupResponse.class)).join();

            if(response == null || response.getCompanies() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all companies request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new CompanyLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<CompanyLookupResponse> getCompanyById(@PathVariable(value = "id") String id) {
        try {
            var query = new FindCompanyByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(CompanyLookupResponse.class)).join();

            if(response == null || response.getCompanies() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get company by id request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new CompanyLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byFilter/{filter}")
    public ResponseEntity<CompanyLookupResponse> searchCompanyByFilter(@PathVariable(value = "filter") String filter) {
        try {
            var query = new SearchCompanyQuery(filter);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(CompanyLookupResponse.class)).join();

            if(response == null || response.getCompanies() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete company search request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new CompanyLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
