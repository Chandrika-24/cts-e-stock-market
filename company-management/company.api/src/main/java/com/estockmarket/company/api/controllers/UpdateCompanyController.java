package com.estockmarket.company.api.controllers;

import com.estockmarket.company.api.commands.UpdateCompanyCommand;
import com.estockmarket.company.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/company/update")
public class UpdateCompanyController {
    private final CommandGateway commandGateway;

    @Autowired
    public UpdateCompanyController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateCompany(@Valid @RequestBody UpdateCompanyCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandGateway.send(command);

            return new ResponseEntity<>(new BaseResponse("Company successfully updated"), HttpStatus.OK);
        } catch(Exception e) {
            var safeErrorMessage = "Error while processing update company request" ;
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
