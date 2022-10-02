package com.ftw.hometerview.auth.service;

import com.ftw.hometerview.auth.util.JwtTokenProvider;
import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.BadRequestException;
import com.ftw.hometerview.member.domain.Member;
import com.ftw.hometerview.member.repository.MemberRepository;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public Map<String, String> getNewAccessToken(HttpServletRequest request, String refreshToken) {
        String accessToken = jwtTokenProvider.resolveToken(request);

        jwtTokenProvider.getPayLoad(refreshToken);
        String memberId = jwtTokenProvider.getExpiredPayLoad(accessToken);

        Member member = memberRepository.findByMemberId(memberId).orElseThrow(
            () -> new BadRequestException(ResponseType.MEMBER_NOT_EXIST_ID)
        );

        String savedRefreshToken = member.getRefreshToken();
        if (savedRefreshToken.equals(refreshToken)) {
            throw new BadRequestException(ResponseType.JWT_NOT_VALID);
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(memberId);

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", newAccessToken);
        return response;
    }
}
