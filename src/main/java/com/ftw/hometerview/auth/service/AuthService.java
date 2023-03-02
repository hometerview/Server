package com.ftw.hometerview.auth.service;

import com.ftw.hometerview.auth.util.JwtTokenProvider;
import com.ftw.hometerview.auth.util.vo.TokenData;
import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.NotFoundException;
import com.ftw.hometerview.core.exception.UnauthorizedException;
import com.ftw.hometerview.core.util.Constants;
import com.ftw.hometerview.member.domain.Member;
import com.ftw.hometerview.member.repository.MemberRepository;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public void reissueAccessTokenToHeader(String memberId, String refreshToken,
        HttpServletResponse response) {

        TokenData refreshTokenData = jwtTokenProvider.getTokenData(refreshToken);
        switch (refreshTokenData.getTokenStatus()) {
            case INVALID -> throw new UnauthorizedException(ResponseType.JWT_NOT_VALID);
            case EXPIRED -> throw new UnauthorizedException(ResponseType.AUTH_REQUIRE_LOGIN);
        }

        Member member = memberRepository.findByOauthId(memberId).orElseThrow(
            () -> new NotFoundException(ResponseType.MEMBER_NOT_EXIST_ID)
        );

        String savedRefreshToken = member.getRefreshToken();
        if (!savedRefreshToken.equals(refreshToken)) {
            throw new UnauthorizedException(ResponseType.JWT_NOT_VALID);
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(memberId);
        log.info("[reissue] success");
        response.setHeader(Constants.AUTH_ACCESS_HEADER_KEY, newAccessToken);
    }
}
