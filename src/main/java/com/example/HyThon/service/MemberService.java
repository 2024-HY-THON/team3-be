package com.example.HyThon.service;

import com.example.HyThon.converter.MemberConverter;
import com.example.HyThon.domain.Member;
import com.example.HyThon.repository.MemberRepository;
import com.example.HyThon.web.dto.MemberRequestDTO;
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

    @Transactional
    public Member signupMember(MemberRequestDTO.MemberSignupDTO request) throws IllegalArgumentException {

        Optional<Member> findMember = memberRepository.findByUserName(request.getUsername());
        if (findMember.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저 이름입니다.");
        }

        Member signupMember = MemberConverter.toMember(request);
        signupMember.encodingPassword(passwordEncoder.encode(request.getPassword()));

        return memberRepository.save(signupMember);
    }
}
