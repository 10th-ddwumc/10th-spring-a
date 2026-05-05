package com.example.umc10th.domain.member.exception;

import com.example.umc10th.domain.member.exception.code.MemberErrorCode;

public class MemberException extends RuntimeException {
    public MemberException(MemberErrorCode message) {
        super(String.valueOf(message));
    }
}
