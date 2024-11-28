package com.example.HyThon.apiPayload.exception.handler;

import com.example.HyThon.apiPayload.code.status.ErrorStatus;
import com.example.HyThon.apiPayload.exception.GeneralException;

public class DiaryHandler extends GeneralException {
    public DiaryHandler(ErrorStatus errorCode) {
        super(errorCode);
    }
}
