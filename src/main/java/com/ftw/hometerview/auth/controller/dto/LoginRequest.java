package com.ftw.hometerview.auth.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {

    private String accessToken;
    private String refreshToken;
}
