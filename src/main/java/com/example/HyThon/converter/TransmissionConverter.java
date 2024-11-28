package com.example.HyThon.converter;

import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.Member;
import com.example.HyThon.domain.Transmission;
import com.example.HyThon.web.dto.TransmissionRequestDTO;
import com.example.HyThon.web.dto.TransmissionResponseDTO;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

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
                .receiverId(transmission.getReceiver())
                .build();
    }
}
