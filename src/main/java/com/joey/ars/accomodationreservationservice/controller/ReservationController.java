package com.joey.ars.accomodationreservationservice.controller;

import com.joey.ars.accomodationreservationservice.dto.ReservationCreateDto;
import com.joey.ars.accomodationreservationservice.repository.MemberRepository;
import com.joey.ars.accomodationreservationservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    private final MemberRepository memberRepository;
    @PostMapping("/reservation")
    public String makeReservation(@RequestBody ReservationCreateDto reservationCreateDto) {
        reservationService.createReservation(reservationCreateDto);
        return "ok";
    }
    @GetMapping("/reservation")
    public String getReservation() {
        return "reservation";
    }
}
