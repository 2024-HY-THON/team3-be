package com.example.HyThon.converter;

import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.Member;
import com.example.HyThon.domain.Transmission;
import com.example.HyThon.web.dto.TransmissionResponseDTO;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RequiredArgsConstructor
public class TransmissionConverter {

    public static Transmission toTransmission(Diary diary, Member member) {
        return Transmission.builder()
                .diary(diary)
                .receiver(member)
                .build();
    }

    public static TransmissionResponseDTO.TransmissionResultDTO toTransmitResultDTO(Transmission transmission) {
        return TransmissionResponseDTO.TransmissionResultDTO.builder()
                .transmissionId(transmission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TransmissionResponseDTO.GetTransmissionResultDTO toGetTransmissionResultDTO(Transmission transmission) {
        String parsedLocalDateTime = transmission.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return TransmissionResponseDTO.GetTransmissionResultDTO.builder()
                .transmissionId(transmission.getId())
                .diaryTitle(transmission.getDiary().getTitle())
                .diaryContent(transmission.getDiary().getContent())
                .sentAt(parsedLocalDateTime)
                .build();
    }

}
