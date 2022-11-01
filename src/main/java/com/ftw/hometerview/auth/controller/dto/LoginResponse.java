package com.ftw.hometerview.auth.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
}
