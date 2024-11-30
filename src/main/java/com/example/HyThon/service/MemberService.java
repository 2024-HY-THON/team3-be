package com.example.HyThon.service;

import com.example.HyThon.apiPayload.code.status.ErrorStatus;
import com.example.HyThon.apiPayload.exception.handler.MemberHandler;
import com.example.HyThon.converter.MemberConverter;
import com.example.HyThon.domain.Member;
import com.example.HyThon.repository.MemberRepository;
import com.example.HyThon.util.JwtUtil;
import com.example.HyThon.web.dto.MemberRequestDTO;
import com.example.HyThon.web.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public Member signupMember(MemberRequestDTO.MemberSignupDTO request) {

        Member findMember = memberRepository.findByName(request.getName());
        if (findMember != null) {
            throw new MemberHandler(ErrorStatus.NICKNAME_ALREADY_EXIST);
        }

        Member signupMember = MemberConverter.toMember(request);
        signupMember.encodingPassword(passwordEncoder.encode(request.getPassword()));

        return memberRepository.save(signupMember);
    }

    public MemberResponseDTO.MemberLoginResultDTO login(MemberRequestDTO.MemberSignupDTO request) {

        Member loginMember = memberRepository.findByName(request.getName());
        if (loginMember == null) {
            throw new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(request.getPassword(), loginMember.getPassword())) {
            throw new MemberHandler(ErrorStatus.PASSWORD_NOT_MATCH);
        }

        String accessToken = jwtUtil.createAccessToken(loginMember.getId(), loginMember.getName(), null);

        return MemberResponseDTO.MemberLoginResultDTO.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .build();
    }
}
