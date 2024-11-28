package com.example.HyThon.domain.enums;

import com.example.HyThon.apiPayload.code.status.ErrorStatus;
import com.example.HyThon.apiPayload.exception.handler.DiaryHandler;

public enum EmotionType {
    HARD(1), GOOD(2), SPECIAL(3);

    private final int value;

    EmotionType(int value) {
        this.value = value;
    }

    public static EmotionType fromValue(int value) {
        for (EmotionType emotion : EmotionType.values()) {
            if (emotion.value == value) {
                return emotion;
            }
        }
        throw new DiaryHandler(ErrorStatus.EMOTION_NOT_FOUND);
    }

}
