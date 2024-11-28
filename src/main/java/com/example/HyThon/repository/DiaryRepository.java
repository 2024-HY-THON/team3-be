package com.example.HyThon.repository;

import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.Member;
import com.example.HyThon.domain.enums.EmotionType;
import com.example.HyThon.domain.enums.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;
import java.util.*;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("SELECT d FROM Diary d WHERE d.writer = :member AND DATE(d.createdAt) = :date")
    Optional<Diary> findByMemberAndDate(Member member, @Param("date") LocalDate date);

    List<Diary> findDiariesBySubjectTypeAndEmotionType(SubjectType subjectType, EmotionType emotionType);
}
