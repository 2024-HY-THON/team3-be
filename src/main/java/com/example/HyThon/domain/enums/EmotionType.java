package com.example.HyThon.domain.enums;

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
        throw new IllegalArgumentException("감정 카테고리가 존재하지 않습니다.");
    }

}
