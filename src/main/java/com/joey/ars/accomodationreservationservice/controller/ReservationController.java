package com.joey.ars.accomodationreservationservice.controller;

import com.joey.ars.accomodationreservationservice.dto.ReservationCreateDto;
import com.joey.ars.accomodationreservationservice.dto.ReservationGetByMemberDto;
import com.joey.ars.accomodationreservationservice.entity.Reservation;
import com.joey.ars.accomodationreservationservice.repository.MemberRepository;
import com.joey.ars.accomodationreservationservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    private final MemberRepository memberRepository;

    @GetMapping("/reservation/{id}")
    public List<ReservationGetByMemberDto> getReservationByMemberId(@PathVariable Long id) {
        return reservationService.getReservationByMemberId(id);
    }

    @PostMapping("/reservation")
    public String makeReservation(@RequestBody ReservationCreateDto reservationCreateDto) {
        reservationService.createReservation(reservationCreateDto);
        return "ok";
    }
}
