package com.estockmarket.company.cmd.api.controllers;

import com.estockmarket.company.cmd.api.commands.RemoveCompanyCommand;
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
@RequestMapping(path = "/api/v1/removeCompany")
public class RemoveCompanyController {

    private final CommandGateway commandGateway;

    @Autowired
    public  RemoveCompanyController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> removeCompany(@PathVariable(value = "id") String id) {
        try {
            commandGateway.send(new RemoveCompanyCommand(id));
            return new ResponseEntity<>(new BaseResponse("Company successfully removed"), HttpStatus.OK);
        } catch(Exception e) {
            var safeErrorMessage = "Error while processing remove company request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
