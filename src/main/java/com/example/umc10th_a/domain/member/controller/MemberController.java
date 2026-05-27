package com.example.umc10th_a.domain.member.controller;

import com.example.umc10th_a.global.security.entity.AuthMember;
import com.example.umc10th_a.domain.member.dto.MemberResDTO;
import com.example.umc10th_a.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/v2/users/me")
    public MemberResDTO.GetInfo getMyInfo(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        return memberService.getInfo(authMember);
    }
}