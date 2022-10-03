package com.ftw.hometerview.auth.service;

import com.ftw.hometerview.auth.controller.dto.LoginRequest;
import com.ftw.hometerview.auth.controller.dto.LoginResponse;
import com.ftw.hometerview.auth.controller.dto.ProviderType;
import com.ftw.hometerview.auth.util.JwtTokenProvider;
import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.BaseException;
import com.ftw.hometerview.member.domain.Member;
import com.ftw.hometerview.member.repository.MemberRepository;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OAuthKakaoServiceImpl implements OAuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public Map<String, Object> getMemberInfoByToken(LoginRequest loginRequest) {
        return WebClient.create()
            .get()
            .uri("https://kapi.kakao.com/v2/user/me")
            .headers(httpHeaders -> httpHeaders.setBearerAuth(loginRequest.getAccessToken()))
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
            .block();
    }

    @Transactional
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // Get user information using Kakao Token
        Map<String, Object> kakaoAttributes = getMemberInfoByToken(loginRequest);
        String memberId = String.valueOf(kakaoAttributes.get("id"));
        Map<String, Object> properties = (Map<String, Object>) kakaoAttributes.get("properties");
        String nickname = String.valueOf(properties.get("nickname"));

        String accessToken = jwtTokenProvider.createAccessToken(memberId);
        String refreshToken = jwtTokenProvider.createRefreshToken(memberId);


        // Check if requester are subscribed to the server through requester member ID
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            ProviderType providerType = member.get().getProviderType();

            if (!providerType.equals(ProviderType.KAKAO)) {
                throw new BaseException(ResponseType.AUTH_NOT_MATCH_PROVIDER);
            }
            member.get().setRefreshToken(refreshToken);
            memberRepository.save(member.get());
        } else {
            memberRepository.save(Member.builder()
                .memberId(memberId)
                .providerType(ProviderType.KAKAO)
                .refreshToken(refreshToken)
                .nickname(nickname)
                .build());
        }
        
        LoginResponse loginResponse = LoginResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
        return loginResponse;
    }
}
