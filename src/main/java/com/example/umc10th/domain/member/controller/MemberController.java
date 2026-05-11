package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/v1/users/me")
    public ApiResponse<MemberResponseDTO.GetInfo> getInfo(
            @RequestParam(name = "memberId") Long memberId
    ){
        MemberRequestDTO.GetInfo dto = new MemberRequestDTO.GetInfo(memberId);
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getInfo(dto));
    }

    @PostMapping("/users")
    public ApiResponse<MemberResponseDTO.JoinDto> join(
            @RequestBody MemberRequestDTO.JoinDto request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                memberService.join(request)
        );
    }

    @GetMapping("/home")
    public ApiResponse<MemberResponseDTO.HomeDto> getHome(
            @RequestParam(name = "memberId") Long memberId
    ) {
        MemberRequestDTO.GetInfo dto = new MemberRequestDTO.GetInfo(memberId);
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                memberService.getHome(dto)
        );
    }
}