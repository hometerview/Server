package com.ftw.hometerview.auth.service;

import com.ftw.hometerview.auth.controller.dto.LoginRequest;
import com.ftw.hometerview.auth.controller.dto.LoginResponse;
import java.util.Map;

public interface OAuthService {

    Map<String, Object> getMemberInfoByToken(LoginRequest loginRequest);

    LoginResponse login(LoginRequest loginRequest);
}
