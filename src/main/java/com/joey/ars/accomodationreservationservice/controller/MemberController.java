package com.joey.ars.accomodationreservationservice.controller;

import com.joey.ars.accomodationreservationservice.dto.MemberRegisterDto;
import com.joey.ars.accomodationreservationservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public String register(@RequestBody MemberRegisterDto memberRegisterDto) throws Exception {
        memberService.register(memberRegisterDto);
        return "Success mamber register";
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwt-test";
    }
}
