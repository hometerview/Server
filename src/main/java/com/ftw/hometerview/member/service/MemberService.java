package com.ftw.hometerview.member.service;

import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.NotFoundException;
import com.ftw.hometerview.member.domain.Member;
import com.ftw.hometerview.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMemberById(String memberId) {
        return this.memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundException(ResponseType.MEMBER_NOT_EXIST_ID));

    }

}
