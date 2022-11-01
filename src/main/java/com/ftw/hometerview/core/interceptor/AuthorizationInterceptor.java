package com.ftw.hometerview.core.interceptor;

import com.ftw.hometerview.auth.service.AuthService;
import com.ftw.hometerview.auth.util.JwtTokenProvider;
import com.ftw.hometerview.auth.util.vo.TokenData;
import com.ftw.hometerview.core.annotation.NonAuthorized;
import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.UnauthorizedException;
import com.ftw.hometerview.core.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {

        log.info("[preHandler] request uri : [{}] {}", request.getMethod(),
            request.getRequestURI());
        if (isNonAuthorize(handler)) {
            return true;
        }

        tokenAuthorize(request, response);

        return true;
    }

    private void tokenAuthorize(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = request.getHeader(Constants.AUTH_ACCESS_HEADER_KEY);
        String refreshToken =  request.getHeader(Constants.AUTH_REFRESH_HEADER_KEY);

        if (!StringUtils.hasText(accessToken) || !StringUtils.hasText(refreshToken)) {
            throw new UnauthorizedException(ResponseType.REQUEST_UNAUTHORIZED);
        }

        TokenData accessTokenData = jwtTokenProvider.getTokenData(accessToken);
        String memberId = accessTokenData.getPayload();

        switch (accessTokenData.getTokenStatus()) {
            case INVALID -> throw new UnauthorizedException(ResponseType.JWT_NOT_VALID);
            case EXPIRED -> authService.reissueAccessTokenToHeader(memberId, refreshToken, response);
        }

        AuthorizationContextHolder.setContext(jwtTokenProvider.getAuthentication(memberId));
    }

    private boolean isNonAuthorize(Object handler) {
        NonAuthorized nonAuthorize = ((HandlerMethod) handler).getMethodAnnotation(
            NonAuthorized.class);
        return nonAuthorize != null;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler,
        @Nullable Exception ex) {
        try {
            AuthorizationContextHolder.clearContext();
        } catch (Exception e) {
            log.error(String.format("[AFTER-COMPLETION] fail to clear auth context >> %s",
                e.getMessage()));    // TODO: @Retry
        }
    }
}
