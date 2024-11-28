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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping()
    @Operation(summary = "일기 작성 API")
    public ApiResponse<DiaryResponseDTO.CreateDiaryResultDTO> createDiary(@AuthenticationPrincipal Member member, @Valid @RequestBody DiaryRequestDTO.CreateDiaryDTO request) {
        Diary diary = diaryService.createDiary(member.getId(),request);
        return ApiResponse.onSuccess(DiaryConverter.toCreateDiaryResult(diary));
    }

    @PatchMapping()
    @Operation(summary = "일기 수정 API")
    public ApiResponse<DiaryResponseDTO.EditDiaryResultDTO> EditDiary(@Valid @RequestBody DiaryRequestDTO.EditDiaryDTO request) {
        Diary diary = diaryService.editDiary(request);
        return ApiResponse.onSuccess(DiaryConverter.toEditDiaryResult(diary));
    }

}
