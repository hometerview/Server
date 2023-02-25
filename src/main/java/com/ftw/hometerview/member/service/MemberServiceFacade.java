package com.ftw.hometerview.member.service;


import com.ftw.hometerview.member.controller.dto.MemberDto;
import com.ftw.hometerview.member.domain.Member;
import com.ftw.hometerview.place.controller.dto.PlaceDto;
import com.ftw.hometerview.place.controller.dto.PlaceDto.CompanyDetail;
import com.ftw.hometerview.place.service.PlaceServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceFacade {

    private final PlaceServiceFacade placeService;
    private final MemberService memberService;


    public MemberDto getMemberInfo(String memberId) {
        Member member = this.memberService.getMemberById(memberId);
        PlaceDto.CompanyDetail companyDetail = new CompanyDetail();
        if (member.getCompanyId().isEmpty()) {
            companyDetail = placeService.getCompanyDetail(member.getCompanyId());
        }
        return MemberDto.of(member, companyDetail);
    }

}
