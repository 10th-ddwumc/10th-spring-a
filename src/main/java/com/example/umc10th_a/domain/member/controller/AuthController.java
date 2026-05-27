package com.example.umc10th_a.domain.member.controller;

import com.example.umc10th_a.domain.member.dto.MemberReqDTO;
import com.example.umc10th_a.domain.member.dto.MemberResDTO;
import com.example.umc10th_a.domain.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public MemberResDTO.Join join(@RequestBody MemberReqDTO.Join request) {
        return authService.join(request);
    }

    @PostMapping("/login")
    public MemberResDTO.Login login(@RequestBody MemberReqDTO.Login request) {
        return authService.login(request);
    }
}