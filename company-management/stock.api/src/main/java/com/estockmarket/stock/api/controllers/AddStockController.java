package com.estockmarket.stock.api.controllers;

import com.estockmarket.stock.api.commands.AddStockCommand;
import com.estockmarket.stock.api.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/stock/add")
public class AddStockController {
    private final CommandGateway commandGateway;

    @Autowired
    public AddStockController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(value = "/{companycode}")
    public ResponseEntity<BaseResponse> registerCompany(@Valid @RequestBody AddStockCommand command,
    @PathVariable(value = "companycode") String companycode) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        command.getStock().setCompanycode(companycode);
        try{

            commandGateway.send(command);
//                            .thenApply(it -> {
//                                return new ResponseEntity<>(new BaseResponse("Stock successfully added"), HttpStatus.CREATED);
//                            }).exceptionally(e -> {
//                System.out.println(e);
//                return new ResponseEntity<>(new BaseResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//            });

            return new ResponseEntity<>(new BaseResponse("Stock successfully added"), HttpStatus.CREATED);

        } catch (Exception e) {
            var safeErrorMessage = "Error while processing add stock request - ";
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
