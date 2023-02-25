package com.ftw.hometerview.member.controller;

import com.ftw.hometerview.core.domain.ResponseEntity;
import com.ftw.hometerview.core.interceptor.AuthUtil;
import com.ftw.hometerview.member.controller.dto.MemberDto;
import com.ftw.hometerview.member.service.MemberServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/member")
public class MemberController {

    private final MemberServiceFacade memberService;

    @GetMapping("/my")
    public ResponseEntity<MemberDto> getLoginMemberInfo() {
        var memberInfo = this.memberService.getMemberInfo(AuthUtil.getCurrentMemberId());
        return ResponseEntity.successResponse(memberInfo);
    }

}
