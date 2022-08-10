package com.ftw.hometerview.core.interceptor;

import com.ftw.hometerview.core.annotation.NonAuthorized;
import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.UnauthorizedException;
import com.ftw.hometerview.core.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final AuthorizationContextHolder authContext = new AuthorizationContextHolder();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {
        if (isNonAuthorize(handler)) {
            return true;
        }
        String userId = request.getHeader(Constants.AUTH_HEADER_KEY);
        if (!StringUtils.hasText(userId)) {
            throw new UnauthorizedException(ResponseType.REQUEST_UNAUTHORIZED);
        }
        authContext.setContext(userId);
        return true;
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
            authContext.clearContext();
        } catch (Exception e) {
            log.error(String.format("[AFTER-COMPLETION] fail to clear auth context >> %s",
                ex.getMessage()));    // TODO: @Retry
        }
    }
}