package com.ftw.hometerview.auth.controller;

import com.ftw.hometerview.auth.controller.dto.LoginRequest;
import com.ftw.hometerview.auth.controller.dto.LoginResponse;
import com.ftw.hometerview.auth.service.AuthService;
import com.ftw.hometerview.auth.service.OAuthKakaoServiceImpl;
import com.ftw.hometerview.core.annotation.NonAuthorized;
import com.ftw.hometerview.core.domain.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final AuthService authService;

    @Operation(summary = "카카오 token을 이용한 서버의 token 발급 받기")
    @NonAuthorized
    @PostMapping("/login/kakao")
    public ResponseEntity<LoginResponse> kakaoLogin(@RequestBody LoginRequest loginRequest) {
        var response = oAuthKakaoServiceImpl.login(LoginRequest.builder()
            .accessToken(loginRequest.getAccessToken())
            .refreshToken(loginRequest.getRefreshToken())
            .build());
        return ResponseEntity.successResponse(response);
    }

    @Operation(summary = "Refresh token을 이용한 Access token 갱신")
    @NonAuthorized
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(HttpServletRequest request, @RequestBody String refreshToken) {
        var response = authService.getNewAccessToken(request, refreshToken);
        return ResponseEntity.successResponse(response);
    }
}
