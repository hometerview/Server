package com.ftw.hometerview.auth.util.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenData {
    public enum TokenStatus {
        EXPIRED, INVALID, VALID;
    }

    TokenStatus tokenStatus;

    String payload;
}
