package com.joey.ars.accomodationreservationservice.service;

import com.joey.ars.accomodationreservationservice.dto.MemberRegisterDto;
import com.joey.ars.accomodationreservationservice.entity.Member;
import com.joey.ars.accomodationreservationservice.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(MemberRegisterDto memberRegisterDto) throws Exception {
        if (memberRepository.findByEmail(memberRegisterDto.getEmail()).isPresent()) {
            throw new Exception("Used email");
        }
        if (memberRepository.findByUsername(memberRegisterDto.getUsername()).isPresent()) {
            throw new Exception("Used username");
        }

        Member member = Member.builder()
                .email(memberRegisterDto.getEmail())
                .password(memberRegisterDto.getPassword())
                .username(memberRegisterDto.getUsername())
                .phone(memberRegisterDto.getPhone())
                .build();
        member.passwordEncode(passwordEncoder);
        memberRepository.save(member);
    }
}
