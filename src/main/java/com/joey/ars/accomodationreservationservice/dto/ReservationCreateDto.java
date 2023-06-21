package com.joey.ars.accomodationreservationservice.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.joey.ars.accomodationreservationservice.entity.Member;
import com.joey.ars.accomodationreservationservice.entity.Reservation;
import com.joey.ars.accomodationreservationservice.entity.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class ReservationCreateDto {
    private LocalDate start_date;
    private LocalDate end_date;
    private Long member_id;
    private Long room_id;

    @Builder
    public ReservationCreateDto(LocalDate start_date, LocalDate end_date, Long member_id, Long room_id) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.member_id = member_id;
        this.room_id = room_id;
    }

    public Reservation toEntity(Member member, Room room) {
        return Reservation.builder()
                .start_date(start_date)
                .end_date(end_date)
                .member_id(member)
                .room_id(room)
                .build();
    }
}
