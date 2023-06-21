package com.joey.ars.accomodationreservationservice.service;

import com.joey.ars.accomodationreservationservice.dto.ReservationCreateDto;
import com.joey.ars.accomodationreservationservice.dto.ReservationGetByMemberDto;
import com.joey.ars.accomodationreservationservice.entity.Member;
import com.joey.ars.accomodationreservationservice.entity.Reservation;
import com.joey.ars.accomodationreservationservice.entity.Room;
import com.joey.ars.accomodationreservationservice.repository.MemberRepository;
import com.joey.ars.accomodationreservationservice.repository.ReservationRepository;
import com.joey.ars.accomodationreservationservice.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public List<ReservationGetByMemberDto> getReservationByMemberId(Long id) {
        List<Reservation> entity = reservationRepository.findByMember_id(id);
        List<ReservationGetByMemberDto> result = new ArrayList<>();
        for(Reservation reservation : entity)
            result.add(this.reservationGetByMemberDto(reservation));
        return result;
    }

    @Transactional
    public Long createReservation(ReservationCreateDto reservationCreateDto) {
        Member member = memberRepository.findById(reservationCreateDto.getMember_id())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        Room room = roomRepository.findById(reservationCreateDto.getRoom_id())
                .orElseThrow(() -> new IllegalArgumentException("방이 존재하지 않습니다."));
        return reservationRepository.save(reservationCreateDto.toEntity(member, room)).getId();
    }

    private ReservationGetByMemberDto reservationGetByMemberDto(Reservation reservation) {
        return ReservationGetByMemberDto.builder()
                .deleted(reservation.getDeleted())
                .start_date(reservation.getStart_date())
                .end_date(reservation.getEnd_date())
                .member_id(reservation.getMember().getId())
                .room_id(reservation.getRoom().getId())
                .build();
    }
}
