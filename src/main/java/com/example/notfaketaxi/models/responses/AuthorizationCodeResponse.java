package com.example.notfaketaxi.models.responses;

import java.util.UUID;

public class AuthorizationCodeResponse extends BaseResponse{
    public UUID AuthorizationCode;

    public AuthorizationCodeResponse(UUID authCode, String message) {
        super(message);
        AuthorizationCode = authCode;
    }
}
