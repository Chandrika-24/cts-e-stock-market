package com.estockmarket.company.api.controllers;

import com.estockmarket.company.api.commands.RegisterCompanyCommand;
import com.estockmarket.company.api.dto.RegisterCompanyResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/company/register")
public class RegisterCompanyController {
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterCompanyController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<RegisterCompanyResponse> registerCompany(@Valid @RequestBody RegisterCompanyCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try{

            commandGateway.send(command);

            return new ResponseEntity<>(new RegisterCompanyResponse(id,"Company successfully registered"), HttpStatus.CREATED);

        } catch (Exception e) {
            var safeErrorMessage = "Error while processing register company request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new RegisterCompanyResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
