package com.ftw.hometerview.auth.util;

import com.ftw.hometerview.auth.util.vo.TokenData;
import com.ftw.hometerview.auth.util.vo.TokenData.TokenStatus;
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

    public TokenData getTokenData(String token) {
        try {
            String payload = parseJwt(token);
            return TokenData.builder()
                .payload(payload)
                .tokenStatus(TokenStatus.VALID)
                .build();
        } catch (ExpiredJwtException e) {
            return TokenData.builder()
                .payload(e.getClaims().getSubject())
                .tokenStatus(TokenStatus.EXPIRED)
                .build();
        } catch (Exception e) {
            return TokenData.builder()
                .tokenStatus(TokenStatus.INVALID)
                .build();
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

    public AuthContent getAuthentication(String memberId) {
        return AuthContent.builder().memberId(memberId).build();
    }
}
