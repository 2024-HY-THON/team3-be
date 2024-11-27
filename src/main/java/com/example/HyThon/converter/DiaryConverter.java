package com.example.HyThon.converter;

import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.enums.EmotionType;
import com.example.HyThon.domain.enums.SubjectType;
import com.example.HyThon.web.dto.DiaryRequestDTO;
import com.example.HyThon.web.dto.DiaryResponseDTO;

import java.time.LocalDateTime;

public class DiaryConverter {

    public static Diary toDiary(DiaryRequestDTO.CreateDiaryDTO request) {

        SubjectType subject = SubjectType.fromValue(request.getSubjectType());
        EmotionType emotion = EmotionType.fromValue(request.getEmotionType());

        return Diary.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .subjectType(subject)
                .emotionType(emotion)
                .build();
    }

    public static DiaryResponseDTO.CreateDiaryResultDTO toCreateDiaryResult(Diary diary) {
        return DiaryResponseDTO.CreateDiaryResultDTO.builder()
                .diaryId(diary.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
