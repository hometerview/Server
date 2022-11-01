package com.ftw.hometerview.auth.controller;

import com.ftw.hometerview.auth.controller.dto.LoginRequest;
import com.ftw.hometerview.auth.controller.dto.LoginResponse;
import com.ftw.hometerview.auth.service.OAuthKakaoServiceImpl;
import com.ftw.hometerview.core.annotation.NonAuthorized;
import com.ftw.hometerview.core.domain.ResponseEntity;
import com.ftw.hometerview.core.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final OAuthKakaoServiceImpl oAuthKakaoServiceImpl;

    @Operation(summary = "카카오 token을 이용한 서버의 token 발급 받기")
    @NonAuthorized
    @PostMapping("/login/kakao")
    public ResponseEntity kakaoLogin(HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
        LoginResponse result = oAuthKakaoServiceImpl.login(loginRequest);
        response.setHeader(Constants.AUTH_ACCESS_HEADER_KEY, result.getAccessToken());
        response.setHeader(Constants.AUTH_REFRESH_HEADER_KEY, result.getRefreshToken());
        return ResponseEntity.successResponse();
    }
}
