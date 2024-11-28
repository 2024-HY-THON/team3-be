package com.example.HyThon.web.controller;

import com.example.HyThon.converter.TransmissionConverter;
import com.example.HyThon.domain.Transmission;
import com.example.HyThon.service.TransmissionService;
import com.example.HyThon.web.dto.TransmissionRequestDTO;
import com.example.HyThon.web.dto.TransmissionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transmission")
public class TranmissionController {

    private final TransmissionService transmissionService;

    @PostMapping()
    public ResponseEntity<TransmissionResponseDTO.TransmissionResultDTO> transmit(@RequestBody TransmissionRequestDTO.TransmitDTO request) {
        Transmission transmission = transmissionService.transmitDiary(request);
        return ResponseEntity.ok(TransmissionConverter.toTransmitResultDTO(transmission));
    }
}
