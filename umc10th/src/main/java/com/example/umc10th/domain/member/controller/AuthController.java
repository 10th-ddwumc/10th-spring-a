package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증(로그인 및 회원가입) API")
public class AuthController {

    private final MemberService memberService;

    // 1. 회원가입 API (SecurityConfig의 /auth/** 허용 경로에 맞춤)
    @PostMapping("/auth/sign-up")
    @Operation(summary = "회원가입 API", description = "새로운 사용자를 등록합니다.")
    public ApiResponse<MemberResDTO.JoinResultDto> join(@RequestBody MemberReqDTO.JoinDto request) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.join(request));
    }

    // 2. 로그인 껍데기 API (Swagger 문서 노출용)
    // 폼 로그인은 application/x-www-form-urlencoded 형식으로 데이터를 받습니다.
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Operation(summary = "로그인 API", description = "Spring Security가 처리하는 폼 로그인입니다. form-data 형식으로 username(이메일)과 password를 전달하세요.")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 실제 동작은 Spring Security의 formLogin 필터가 요청을 가로채서 수행합니다.
        // 이 메서드는 오직 Swagger 문서에 /login 엔드포인트를 노출시키기 위한 껍데기이므로 로직을 비워둡니다.
        throw new IllegalStateException("이 메서드는 호출되지 않습니다.");
    }
}