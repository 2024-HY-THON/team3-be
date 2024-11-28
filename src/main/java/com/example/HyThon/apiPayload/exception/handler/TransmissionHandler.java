package com.example.HyThon.apiPayload.exception.handler;

import com.example.HyThon.apiPayload.code.status.ErrorStatus;
import com.example.HyThon.apiPayload.exception.GeneralException;

public class TransmissionHandler extends GeneralException {
    public TransmissionHandler(ErrorStatus errorCode) {
        super(errorCode);
    }
}
