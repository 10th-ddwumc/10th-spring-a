package com.example.umc10th_a.domain.member.controller;

import com.example.umc10th_a.domain.member.dto.MemberReqDTO;
import com.example.umc10th_a.domain.member.dto.MemberResDTO;
import com.example.umc10th_a.domain.member.exception.MemberSuccessCode;
import com.example.umc10th_a.domain.member.service.MemberService;
import com.example.umc10th_a.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/test")
    public ApiResponse<String> test() {
        return ApiResponse.onSuccess(
                MemberSuccessCode.TEST_SUCCESS,
                "test"
        );
    }

    @PostMapping("/query-parameter")
    public ApiResponse<String> queryParameter(
            @RequestParam String queryParameter
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.QUERY_PARAMETER_SUCCESS,
                memberService.singleParameter(queryParameter)
        );
    }

    @PostMapping("/request-body")
    public ApiResponse<MemberResDTO.RequestBody> requestBody(
            @RequestBody MemberReqDTO.RequestBody dto
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.REQUEST_BODY_SUCCESS,
                memberService.requestBody(dto)
        );
    }

    @PostMapping("/me")
    public ApiResponse<MemberResDTO.MyPageResponseDTO> getMyPage(
            @RequestBody MemberReqDTO.MyPageRequestDTO request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.GET_MY_PAGE,
                memberService.getMyPage(request)
        );
    }
}