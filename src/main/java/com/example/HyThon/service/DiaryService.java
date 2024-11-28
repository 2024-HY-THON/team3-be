package com.example.HyThon.service;

import com.example.HyThon.converter.DiaryConverter;
import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.Member;
import com.example.HyThon.repository.DiaryRepository;
import com.example.HyThon.repository.MemberRepository;
import com.example.HyThon.web.dto.DiaryRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Diary createDiary(DiaryRequestDTO.CreateDiaryDTO request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        Diary newDiary = DiaryConverter.toDiary(request);
        newDiary.setMember(member);

        return diaryRepository.save(newDiary);
    }

    @Transactional
    public Diary editDiary(DiaryRequestDTO.EditDiaryDTO request) {

        Diary diary = diaryRepository.findById(request.getDiaryId())
                .orElseThrow(() -> new IllegalArgumentException("일기가 존재하지 않습니다."));

        DiaryConverter.toUpdateDiary(diary, request);

        return diaryRepository.save(diary);
    }

}
