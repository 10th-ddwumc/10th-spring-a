package com.example.umc10th_a.domain.member.service;

import com.example.umc10th_a.domain.member.converter.MemberConverter;
import com.example.umc10th_a.domain.member.dto.MemberReqDTO;
import com.example.umc10th_a.domain.member.dto.MemberResDTO;
import com.example.umc10th_a.domain.member.entity.Member;
import com.example.umc10th_a.domain.member.exception.MemberErrorCode;
import com.example.umc10th_a.domain.member.exception.MemberException;
import com.example.umc10th_a.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String singleParameter(String queryParameter) {
        return queryParameter;
    }

    public MemberResDTO.RequestBody requestBody(MemberReqDTO.RequestBody dto) {
        return MemberConverter.toRequestBody(
                dto.getStringTest(),
                dto.getLongTest()
        );
    }

    public MemberResDTO.MyPageResponseDTO getMyPage(MemberReqDTO.MyPageRequestDTO request) {
        Member member = memberRepository.findById(request.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMyPageResponseDTO(member);
    }
}