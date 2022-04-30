package com.estockmarket.company.cmd.api.dto;

import com.estockmarket.company.core.dto.BaseResponse;

public class RegisterCompanyResponse extends BaseResponse {
    private String id;
    public RegisterCompanyResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
