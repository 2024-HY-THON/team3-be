package com.example.HyThon.domain.enums;

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
        throw new IllegalArgumentException("소재 카테고리가 존재하지 않습니다.");
    }
}
