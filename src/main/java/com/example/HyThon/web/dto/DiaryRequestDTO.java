package com.example.HyThon.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

public class DiaryRequestDTO {

    @Getter
    public static class CreateDiaryDTO {

        @NotNull(message = "일기 제목을 입력해 주세요.")
        @Length(max = 20)
        String title;

        @Length(max = 400)
        @NotNull(message = "일기 내용을 입력해 주세요.")
        String content;

        @Min(1)
        @Max(9)
        @NotNull(message = "소재를 선택해 주세요.")
        Integer subjectType;

        @Min(1)
        @Max(3)
        @NotNull(message = "감정을 선택해 주세요.")
        Integer emotionType;

    }

    @Getter
    public static class EditDiaryDTO {

        @NotNull(message = "일기 번호는 필수 입력 값입니다.")
        Long diaryId;

        @NotNull(message = "일기 제목은 필수 입력 값입니다.")
        @Length(max = 20)
        String title;

        @Length(max = 400)
        @NotNull(message = "일기 내용은 필수 입력 값입니다.")
        String content;

        @Min(1)
        @Max(9)
        @NotNull(message = "소재 번호는 필수 입력 값입니다.")
        Integer subjectType;

        @Min(1)
        @Max(3)
        @NotNull(message = "감정 번호는 필수 입력 값입니다.")
        Integer emotionType;

    }

}
