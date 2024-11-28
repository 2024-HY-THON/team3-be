package com.example.HyThon.apiPayload.exception.handler;

import com.example.HyThon.apiPayload.code.status.ErrorStatus;
import com.example.HyThon.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(ErrorStatus errorCode) {
        super(errorCode);
    }
}