package com.example.HyThon.web.controller;

import com.example.HyThon.apiPayload.ApiResponse;
import com.example.HyThon.converter.DiaryConverter;
import com.example.HyThon.domain.Diary;
import com.example.HyThon.domain.Member;
import com.example.HyThon.service.DiaryService;
import com.example.HyThon.web.dto.DiaryRequestDTO;
import com.example.HyThon.web.dto.DiaryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping()
    @Operation(summary = "일기 작성 API")
    public ApiResponse<DiaryResponseDTO.CreateDiaryResultDTO> createDiary(@AuthenticationPrincipal Member member,
                                                                          @Valid @RequestBody DiaryRequestDTO.CreateDiaryDTO request) {
        Diary diary = diaryService.createDiary(member.getId(),request);
        return ApiResponse.onSuccess(DiaryConverter.toCreateDiaryResult(diary));
    }

    @PatchMapping()
    @Operation(summary = "일기 수정 API")
    public ApiResponse<DiaryResponseDTO.EditDiaryResultDTO> editDiary(@Valid @RequestBody DiaryRequestDTO.EditDiaryDTO request) {
        Diary diary = diaryService.editDiary(request);
        return ApiResponse.onSuccess(DiaryConverter.toEditDiaryResult(diary));
    }

    @GetMapping()
    @Operation(summary = "일기 조회 API")
    public ApiResponse<DiaryResponseDTO.GetDiaryResultDTO> getDiary(@AuthenticationPrincipal Member member,
                                                                    @DateTimeFormat(pattern = "YYYY-MM-DD") @RequestParam("date") LocalDate date) {
        Diary diary = diaryService.getDiary(member, date);
        return ApiResponse.onSuccess(DiaryConverter.toGetDiaryResult(diary));
    }

    @GetMapping("/today")
    @Operation(summary = "오늘 일기 작성 여부 확인 API")
    public ApiResponse<DiaryResponseDTO.CheckTodayResultDTO> checkTodayDiary(@AuthenticationPrincipal Member member) {
        Boolean isWritten = diaryService.checkTodayDiary(member);
        return ApiResponse.onSuccess(DiaryConverter.toCheckTodayDiaryResult(isWritten));
    }

}
