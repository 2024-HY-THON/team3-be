package com.example.HyThon.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

public class TransmissionRequestDTO {

    @Getter
    public static class TransmitDTO {

        @NotNull(message = "일기 번호는 필수 입력 값입니다.")
        private Long diaryId;
    }
}
