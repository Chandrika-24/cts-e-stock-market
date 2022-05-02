package com.estockmarket.company.api.controllers;

import com.estockmarket.company.api.commands.RemoveCompanyCommand;
import com.estockmarket.company.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/company/delete")
public class RemoveCompanyController {

    private final CommandGateway commandGateway;

    @Autowired
    public  RemoveCompanyController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{companycode}")
    public ResponseEntity<BaseResponse> removeCompany(@PathVariable(value = "companycode") String companycode) {
        try {
            commandGateway.send(new RemoveCompanyCommand(companycode));
            return new ResponseEntity<>(new BaseResponse("Company successfully removed"), HttpStatus.OK);
        } catch(Exception e) {
            var safeErrorMessage = "Error while processing remove company request for company code - " + companycode;
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
