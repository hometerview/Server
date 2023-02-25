package com.ftw.hometerview.member.controller.dto;

import com.ftw.hometerview.member.domain.Member;
import com.ftw.hometerview.member.domain.Member.Certification;
import com.ftw.hometerview.place.controller.dto.PlaceDto.CompanyDetail;
import lombok.Builder;

@Builder
public class MemberDto {

    String id;
    String nickname;
    Certification certification;
    CompanyDetail companyDetail;


    public static MemberDto of(Member member, CompanyDetail companyDetail) {
        return MemberDto.builder()
            .id(member.getId())
            .nickname(member.getNickname())
            .certification(member.getCertification())
            .companyDetail(companyDetail)
            .build();
    }

}
