package com.example.HyThon.web.controller;

import com.example.HyThon.apiPayload.ApiResponse;
import com.example.HyThon.converter.TransmissionConverter;
import com.example.HyThon.domain.Member;
import com.example.HyThon.domain.Transmission;
import com.example.HyThon.service.TransmissionService;
import com.example.HyThon.web.dto.TransmissionRequestDTO;
import com.example.HyThon.web.dto.TransmissionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transmission")
public class TransmissionController {

    private final TransmissionService transmissionService;

    @PostMapping("/")
    @Operation(summary = "편지 수신 API")
    public ApiResponse<TransmissionResponseDTO.TransmissionResultDTO> transmit(@AuthenticationPrincipal Member member,
                                                                               @Valid @RequestBody TransmissionRequestDTO.TransmitDTO request) {
        Transmission transmission = transmissionService.transmitDiary(member, request);
        return ApiResponse.onSuccess(TransmissionConverter.toTransmitResultDTO(transmission));
    }

    @GetMapping("/")
    @Operation(summary = "받은 편지 조회 API")
    public ApiResponse<TransmissionResponseDTO.GetTransmissionResultDTO> getTransmission(@AuthenticationPrincipal Member member,
                                                                                         @DateTimeFormat(pattern = "YYYY-MM-DD") @RequestParam("date") LocalDate date) {
        Transmission transmission = transmissionService.getTransmission(member, date);
        return ApiResponse.onSuccess(TransmissionConverter.toGetTransmissionResultDTO(transmission));
    }

}
