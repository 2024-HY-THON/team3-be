package com.example.HyThon.converter;

import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.enums.EmotionType;
import com.example.HyThon.domain.enums.SubjectType;
import com.example.HyThon.web.dto.DiaryRequestDTO;
import com.example.HyThon.web.dto.DiaryResponseDTO;

import java.time.LocalDateTime;

public class DiaryConverter {

    public static Diary toDiary(DiaryRequestDTO.CreateDiaryDTO request) {

        SubjectType subjectType = null;
        switch (request.getSubjectType()){
            case 1:
                subjectType = SubjectType.WORK; break;
            case 2:
                subjectType = SubjectType.STUDY; break;
            case 3:
                subjectType = SubjectType.FAMILY; break;
            case 4:
                subjectType = SubjectType.FRIENDS; break;
            case 5:
                subjectType = SubjectType.HEALTH; break;
            case 6:
                subjectType = SubjectType.WEATHER; break;
            case 7:
                subjectType = SubjectType.LOVE; break;
            case 8:
                subjectType = SubjectType.MONEY; break;
            case 9:
                subjectType = SubjectType.OTHER; break;
        }

        EmotionType emotionType = null;
        switch (request.getEmotionType()){
            case 1:
                emotionType = EmotionType.HARD; break;
            case 2:
                emotionType = EmotionType.GOOD; break;
            case 3:
                emotionType = EmotionType.SPECIAL; break;
        }

        return Diary.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .subjectType(subjectType)
                .emotionType(emotionType)
                .build();
    }

    public static DiaryResponseDTO.CreateDiaryResultDTO toCreateDiaryResult(Diary diary) {
        return DiaryResponseDTO.CreateDiaryResultDTO.builder()
                .diaryId(diary.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
