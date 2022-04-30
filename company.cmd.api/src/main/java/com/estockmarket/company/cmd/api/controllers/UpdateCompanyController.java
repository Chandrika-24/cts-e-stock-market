package com.estockmarket.company.cmd.api.controllers;

import com.estockmarket.company.cmd.api.commands.UpdateCompanyCommand;
import com.estockmarket.company.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/updateCompany")
public class UpdateCompanyController {
    private final CommandGateway commandGateway;

    @Autowired
    public UpdateCompanyController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateCompany(@PathVariable(value = "id") String id,
                                                      @Valid @RequestBody UpdateCompanyCommand command) {
        try {
            command.setId(id);
            commandGateway.send(command);

            return new ResponseEntity<>(new BaseResponse("Company successfully updated"), HttpStatus.OK);
        } catch(Exception e) {
            var safeErrorMessage = "Error while processing update company request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
