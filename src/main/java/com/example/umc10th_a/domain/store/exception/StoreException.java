package com.example.umc10th_a.domain.store.exception;

import com.example.umc10th_a.global.exception.BaseErrorCode;
import com.example.umc10th_a.global.exception.ProjectException;

public class StoreException extends ProjectException {

    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}