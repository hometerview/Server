package com.ftw.hometerview.core.domain;

import com.ftw.hometerview.core.util.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseType {

    // COMMON
    SUCCESS("CM00", "success :)"),
    FAILURE("CM99", "failure :("), // Internal Server Error

    ARGUMENT_NOT_VALID("CM01", "Argument 유효성 검증에 실패하였습니다."),
    REQUEST_NOT_VALID("CM02", "유효하지 않는 요청입니다."),
    REQUEST_UNAUTHORIZED("CM03", "비인증된 요청입니다."),
    JWT_NOT_VALID("CM04", "올바르지 않은 토큰입니다."),
    JWT_MALFORMED("CM05", "위조된 토큰입니다."),
    JWT_UNSUPPORTED("CM06", "지원하지 않는 토큰입니다."),
    JWT_SIGNATURE("CM07", "시그니처 검증에 실패한 토큰입니다."),
    JWT_EXPIRED("CM08", "만료된 토큰입니다."),
    JWT_NULL_OR_EMPTY("CM09", "토큰이 없거나 값이 비어있습니다."),
    JWT_HEADER_PREFIX("CM10", "토큰 값은 " + Constants.AUTH_ACCESS_HEADER_KEY + " 로 시작해야 합니다."),

    // AUTH
    AUTH_NULL_TOKEN("AU01", "토큰을 찾을 수 없습니다."),
    AUTH_NOT_SAME_TOKEN("AU02", "저장된 토큰과 일치하지 않습니다."),
    AUTH_NOT_SAME_USER("AU03", "Access와 Refresh 토큰의 사용자가 일치하지 않습니다."),
    AUTH_NOT_SUPPORT_PROVIDER("AU06", "지원하지 않는 Provider입니다."),
    AUTH_NOT_MATCH_PROVIDER("AU07", "가입된 OAuth 제공자와 일치하지 않습니다."),
    AUTH_REQUIRE_LOGIN("AU08", "재로그인이 필요합니다."),

    // MEMBER
    MEMBER_NOT_EXIST_ID("ME01", "존재하지 않는 사용자입니다."),
    MEMBER_NOT_MATCH_AUTH_TYPE("ME02", "가입 인증 유형이 일치하지 않습니다."),

    // REVIEW
    REVIEW_NOT_EXIST("RE01", "존재하지 않는 리뷰입니다."),
    REVIEW_NOT_AUTHOR("RE02", "작성자가 아닙니다.");

    private final String code;
    private final String message;
}
