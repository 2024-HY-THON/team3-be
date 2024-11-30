package com.example.HyThon.converter;

import com.example.HyThon.domain.Member;
import com.example.HyThon.web.dto.MemberRequestDTO;
import com.example.HyThon.web.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MemberConverter {

    public static Member toMember(MemberRequestDTO.MemberSignupDTO request) {
        return Member.builder()
                .name(request.getName())
                .password(request.getPassword())
                .build();
    }

    public static MemberResponseDTO.MemberSignupResultDTO toSignupResultDTO(Member member) {
        return MemberResponseDTO.MemberSignupResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
