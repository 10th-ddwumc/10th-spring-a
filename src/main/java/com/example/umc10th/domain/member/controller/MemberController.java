package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResponseDTO.GetInfo> getInfo(
            @RequestBody MemberRequestDTO.GetInfo dto
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    // 회원가입
    @PostMapping("/users")
    public ApiResponse<MemberResponseDTO.JoinDto> join(
            @RequestBody MemberRequestDTO.JoinDto request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                memberService.join(request)
        );
    }

    // 홈화면 조회
    @GetMapping("/home")
    public ApiResponse<MemberResponseDTO.HomeDto> getHome(
            @RequestBody MemberRequestDTO.GetInfo dto
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                memberService.getHome(dto)
        );
    }
}
