package com.example.HyThon.domain.enums;

import com.example.HyThon.apiPayload.code.status.ErrorStatus;
import com.example.HyThon.apiPayload.exception.handler.DiaryHandler;

public enum SubjectType {
    WORK(1), STUDY(2), FAMILY(3),
    FRIENDS(4), HEALTH(5), WEATHER(6),
    LOVE(7), MONEY(8), OTHER(9);

    private final int value;

    SubjectType(int value) {
        this.value = value;
    }

    public static SubjectType fromValue(int value) {
        for (SubjectType subject : SubjectType.values()) {
            if (subject.value == value) {
                return subject;
            }
        }
        throw new DiaryHandler(ErrorStatus.SUBJECT_NOT_FOUND);
    }
}
