package com.example.HyThon.service;

import com.example.HyThon.apiPayload.code.status.ErrorStatus;
import com.example.HyThon.apiPayload.exception.handler.DiaryHandler;
import com.example.HyThon.apiPayload.exception.handler.MemberHandler;
import com.example.HyThon.converter.DiaryConverter;
import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.Member;
import com.example.HyThon.repository.DiaryRepository;
import com.example.HyThon.repository.MemberRepository;
import com.example.HyThon.web.dto.DiaryRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Diary createDiary(Long memberId, DiaryRequestDTO.CreateDiaryDTO request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Optional<Diary> findDiary = diaryRepository.findByMemberAndDate(member, LocalDate.now());
        if (findDiary.isPresent())
            throw new DiaryHandler(ErrorStatus.DIARY_ALREADY_EXIST);

        Diary newDiary = DiaryConverter.toDiary(request);
        newDiary.setMember(member);

        return diaryRepository.save(newDiary);
    }

    @Transactional
    public Diary editDiary(Long memberId, DiaryRequestDTO.EditDiaryDTO request) {

        Diary diary = diaryRepository.findById(request.getDiaryId())
                .orElseThrow(() -> new DiaryHandler(ErrorStatus.DIARY_NOT_FOUND));
        if (!Objects.equals(diary.getWriter().getId(), memberId))
            throw new DiaryHandler(ErrorStatus.MEMBER_NOT_MATCH);
        if (!Objects.equals(diary.getCreationDate(), LocalDate.now()))
            throw new DiaryHandler(ErrorStatus.NOT_TODAY_DIARY);

        DiaryConverter.toUpdateDiary(diary, request);

        return diaryRepository.save(diary);
    }

    @Transactional
    public Diary getDiary(Member member, LocalDate date) {

        Diary diary = diaryRepository.findByMemberAndDate(member, date)
                .orElseThrow(() -> new DiaryHandler(ErrorStatus.DIARY_NOT_FOUND));

        return diary;
    }

    public Boolean checkTodayDiary(Member member) {

        LocalDate date = LocalDate.now();
        Optional<Diary> diary = diaryRepository.findByMemberAndDate(member, date);

        return diary.isPresent();
    }

}
