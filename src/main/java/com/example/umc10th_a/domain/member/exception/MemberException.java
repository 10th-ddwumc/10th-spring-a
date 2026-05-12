package com.example.umc10th_a.domain.member.exception;

import com.example.umc10th_a.global.exception.BaseErrorCode;
import com.example.umc10th_a.global.exception.ProjectException;

public class MemberException extends ProjectException {

    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}