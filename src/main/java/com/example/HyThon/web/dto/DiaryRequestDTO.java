package com.example.HyThon.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

public class DiaryRequestDTO {

    @Getter
    public static class CreateDiaryDTO {

        @NotNull
        Long memberId;

        @NotNull(message = "제목을 입력해 주세요.")
        @Length(max = 20)
        String title;

        @NotNull(message = "내용을 입력해 주세요.")
        String content;

        @NotNull(message = "소재를 선택해 주세요.")
        Integer subjectType;

        @NotNull(message = "감정을 선택해 주세요.")
        Integer emotionType;

    }
}
