package com.estockmarket.company.cmd.api.controllers;

import com.estockmarket.company.cmd.api.commands.RegisterCompanyCommand;
import com.estockmarket.company.cmd.api.dto.RegisterCompanyResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/registerCompany")
public class RegisterCompanyController {
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterCompanyController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<RegisterCompanyResponse> registerCompany(@RequestBody RegisterCompanyCommand command) {
        command.setId(UUID.randomUUID().toString());
        try{

            commandGateway.send(command);

            return new ResponseEntity<>(new RegisterCompanyResponse("Company successfully registered"), HttpStatus.CREATED);

        } catch (Exception e) {
            var safeErrorMessage = "Error while processing register company request for id - " + command.getId();
            System.out.println(e.toString());

            return new ResponseEntity<>(new RegisterCompanyResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
