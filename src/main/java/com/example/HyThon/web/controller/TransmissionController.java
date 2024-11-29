package com.example.HyThon.web.controller;

import com.example.HyThon.apiPayload.ApiResponse;
import com.example.HyThon.converter.TransmissionConverter;
import com.example.HyThon.domain.Transmission;
import com.example.HyThon.service.TransmissionService;
import com.example.HyThon.web.dto.TransmissionRequestDTO;
import com.example.HyThon.web.dto.TransmissionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transmission")
public class TransmissionController {

    private final TransmissionService transmissionService;

    @PostMapping("/")
    @Operation(summary = "편지 전송 API")
    public ApiResponse<TransmissionResponseDTO.TransmissionResultDTO> transmit(@Valid @RequestBody TransmissionRequestDTO.TransmitDTO request) {
        Transmission transmission = transmissionService.transmitDiary(request);
        return ApiResponse.onSuccess(TransmissionConverter.toTransmitResultDTO(transmission));
    }
}
