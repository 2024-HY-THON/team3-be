package com.example.HyThon.web.controller;

import com.example.HyThon.apiPayload.ApiResponse;
import com.example.HyThon.converter.MemberConverter;
import com.example.HyThon.domain.Member;
import com.example.HyThon.service.MemberService;
import com.example.HyThon.web.dto.MemberRequestDTO;
import com.example.HyThon.web.dto.MemberResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API")
    public ApiResponse<MemberResponseDTO.MemberSignupResultDTO> signup(@Valid @RequestBody MemberRequestDTO.MemberSignupDTO request) {
        Member member = memberService.signupMember(request);
        return ApiResponse.onSuccess(MemberConverter.toSignupResultDTO(member));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 API")
    public ApiResponse<MemberResponseDTO.MemberLoginResultDTO> login(@Valid @RequestBody MemberRequestDTO.MemberSignupDTO request) {
    MemberResponseDTO.MemberLoginResultDTO accessToken = memberService.login(request);
        return ApiResponse.onSuccess(accessToken);
    }
}
