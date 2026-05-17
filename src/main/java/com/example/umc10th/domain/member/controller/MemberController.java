package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 관련 API", description = "회원가입, 내 정보 조회, 홈 화면 데이터 조회를 위한 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "내 정보 조회 API", description = "사용자 ID를 통해 내 정보를 조회합니다.")
    @GetMapping("/v1/users/me")
    public ApiResponse<MemberResponseDTO.GetInfo> getInfo(
            @RequestParam(name = "memberId") Long memberId
    ){
        MemberRequestDTO.GetInfo dto = new MemberRequestDTO.GetInfo(memberId);
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getInfo(dto));
    }

    @Operation(summary = "회원가입 API", description = "이메일, 이름 등 회원가입에 필요한 정보를 받아 새로운 회원을 등록합니다.")
    @PostMapping("/users")
    public ApiResponse<MemberResponseDTO.JoinDto> join(
            @RequestBody MemberRequestDTO.JoinDto request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                memberService.join(request)
        );
    }

    @Operation(summary = "홈 화면 조회 API", description = "메인 홈 화면에 필요한 닉네임, 포인트 등의 데이터를 조회합니다.")
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

    @Operation(summary = "로그인 API", description = "로그인에 필요한 데이터를 조회합니다.")
    @PostMapping("/login")
    public ApiResponse<MemberResponseDTO.LoginResultDto> login(
            @RequestBody @Valid MemberRequestDTO.LoginDto request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                memberService.login(request)
        );
    }
}