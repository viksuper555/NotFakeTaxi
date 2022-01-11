package com.notfaketaxi.models.responses;

import java.util.UUID;

public class AccessTokenResponse extends BaseResponse{
    public UUID AccessToken;

    public AccessTokenResponse(UUID accessToken, String message) {
        super(message);
        AccessToken = accessToken;
    }
}
