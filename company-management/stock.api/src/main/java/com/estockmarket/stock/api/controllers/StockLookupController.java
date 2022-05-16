package com.estockmarket.stock.api.controllers;

import com.estockmarket.stock.api.dto.StockLookupResponse;
import com.estockmarket.stock.api.queries.GetAllStocksByCompanyCodeQuery;
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
@RequestMapping(path = "/stock/get")
public class StockLookupController {

    private final QueryGateway queryGateway;

    @Autowired
    public StockLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/{companycode}")
    public ResponseEntity<StockLookupResponse> getCompanyById(@PathVariable(value = "companycode") String companycode) {
        try {
            var query = new GetAllStocksByCompanyCodeQuery(companycode);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if(response == null || response.getStocks() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get stocks by company code request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new StockLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
