package com.example.HyThon.web.dto;

import com.example.HyThon.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransmissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransmissionResultDTO {
        Long transmissionId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTransmissionResultDTO {
        Long transmissionId;
        String diaryTitle;
        String diaryContent;
        String sentAt;
        String writtenAt;
    }

}
