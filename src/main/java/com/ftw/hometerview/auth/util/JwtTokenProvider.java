package com.ftw.hometerview.auth.util;

import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.UnauthorizedException;
import com.ftw.hometerview.core.interceptor.AuthContent;
import com.ftw.hometerview.core.util.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${jwt.access-token.expire-length}")
    private long accessTokenValidityInMilliseconds;

    @Value("${jwt.refresh-token.expire-length}")
    private long refreshTokenValidityInMilliseconds;

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    public String createAccessToken(String payload) {
        return createToken(payload, accessTokenValidityInMilliseconds);
    }

    public String createRefreshToken() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        return createToken(generatedString, refreshTokenValidityInMilliseconds);
    }

    private String createToken(String payload, long expireLength) {
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();
        Date validity = new Date(now.getTime() + expireLength);
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
    }

    public String getPayload(String token, boolean throwExpiredException) {
        try {
            return parseJwt(token);
        } catch (ExpiredJwtException e) {
            if (throwExpiredException) {
                throw new UnauthorizedException(ResponseType.JWT_EXPIRED);
            }
            return e.getClaims().getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new UnauthorizedException((ResponseType.JWT_NOT_VALID));
        }
    }

    public boolean isExpiredToken(String token) {
        try {
            parseJwt(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidToken(String token) {
        try {
            parseJwt(token);
            return true;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String parseJwt(String token) throws JwtException, IllegalArgumentException {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public String resolveAccessToken(HttpServletRequest req) {
        return req.getHeader(Constants.AUTH_ACCESS_HEADER_KEY);
    }

    public String resolveRefreshToken(HttpServletRequest req) {
        return req.getHeader(Constants.AUTH_REFRESH_HEADER_KEY);
    }

    public AuthContent getAuthentication(String token) {
        String memberId = this.getPayload(token, true);
        return AuthContent.builder().memberId(memberId).build();
    }
}
