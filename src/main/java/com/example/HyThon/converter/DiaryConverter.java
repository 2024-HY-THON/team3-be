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

    public static void toUpdateDiary(Diary diary, DiaryRequestDTO.EditDiaryDTO request) {
        if (request.getTitle() != null) {
            diary.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            diary.setContent(request.getContent());
        }
        if (request.getSubjectType() != null) {
            SubjectType subject = SubjectType.fromValue(request.getSubjectType());
            diary.setSubjectType(subject);
        }
        if (request.getEmotionType() != null) {
            EmotionType emotion = EmotionType.fromValue(request.getEmotionType());
            diary.setEmotionType(emotion);
        }
    }

    public static DiaryResponseDTO.EditDiaryResultDTO toEditDiaryResult(Diary diary) {
        return DiaryResponseDTO.EditDiaryResultDTO.builder()
                .diaryId(diary.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
