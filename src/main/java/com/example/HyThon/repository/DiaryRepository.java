package com.example.HyThon.repository;

import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.enums.EmotionType;
import com.example.HyThon.domain.enums.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findDiariesBySubjectTypeAndEmotionType(SubjectType subjectType, EmotionType emotionType);
}
