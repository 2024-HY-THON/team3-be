package com.example.HyThon.service;

import com.example.HyThon.apiPayload.code.status.ErrorStatus;
import com.example.HyThon.apiPayload.exception.handler.DiaryHandler;
import com.example.HyThon.apiPayload.exception.handler.TransmissionHandler;
import com.example.HyThon.converter.TransmissionConverter;
import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.Member;
import com.example.HyThon.domain.Transmission;
import com.example.HyThon.domain.enums.EmotionType;
import com.example.HyThon.domain.enums.SubjectType;
import com.example.HyThon.repository.DiaryRepository;
import com.example.HyThon.repository.MemberRepository;
import com.example.HyThon.repository.TransmissionRepository;
import com.example.HyThon.web.dto.TransmissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class TransmissionService {

    private final TransmissionRepository transmissionRepository;
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    public Transmission transmitDiary(TransmissionRequestDTO.TransmitDTO request) {

        // 내 일기 찾기
        Diary findDiary = diaryRepository.findById(request.getDiaryId())
                .orElseThrow(() -> new DiaryHandler(ErrorStatus.DIARY_NOT_FOUND));
        SubjectType subjectType = findDiary.getSubjectType();
        EmotionType emotionType = findDiary.getEmotionType();
        LocalDate writingDate = findDiary.getCreationDate();

        // 내 일기와 소재, 감정이 동일한 일기 리스트 만들기
        List<Diary> diaryList = diaryRepository.findDiariesBySubjectTypeAndEmotionTypeAndCreationDate(subjectType, emotionType, writingDate);
        if (diaryList.isEmpty()) {
            throw new TransmissionHandler(ErrorStatus.TRANSMISSION_NOT_FOUND);
        }

        // 일기 리스트에서 랜덤으로 하나 뽑기 (내 일기와는 다른 일기)
        Diary randomDiary = randomDiary(diaryList, findDiary);

        // 수신자의 일기와 내 번호 저장하기
        Transmission transmission = TransmissionConverter.toTransmission(randomDiary, findDiary.getWriter());
        return transmissionRepository.save(transmission);
    }

    public Diary randomDiary(List<Diary> diaryList, Diary findDiary) {
        Random random = new Random();

        while (true) {
            Diary diary = diaryList.get(random.nextInt(diaryList.size()));
            if (diary.getId() != findDiary.getId()) {
                return diary;
            }
        }
    }

    public Transmission getTransmission(Member member, LocalDate date) {

        Transmission transmission = transmissionRepository.findByMemberAndDate(member, date)
                .orElseThrow(() -> new TransmissionHandler(ErrorStatus.RECEIVED_NOT_FOUND));

        return transmission;
    }

}
