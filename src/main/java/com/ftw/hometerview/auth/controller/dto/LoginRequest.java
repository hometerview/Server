package com.ftw.hometerview.auth.controller.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String accessToken;
    private String refreshToken;
}
