package com.greatbee.product.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public class WeiXinOpenIdResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    private String openid;

    private String scope;
}
