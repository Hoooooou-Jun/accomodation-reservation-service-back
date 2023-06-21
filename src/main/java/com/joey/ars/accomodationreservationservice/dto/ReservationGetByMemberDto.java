package com.joey.ars.accomodationreservationservice.dto;

import com.joey.ars.accomodationreservationservice.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class ReservationGetByMemberDto {
    private Boolean deleted;
    private LocalDate start_date;
    private LocalDate end_date;
    private Long member_id;
    private Long room_id;

    @Builder
    public ReservationGetByMemberDto(Boolean deleted, LocalDate start_date, LocalDate end_date, Long member_id, Long room_id) {
        this.deleted = deleted;
        this.start_date = start_date;
        this.end_date = end_date;
        this.member_id = member_id;
        this.room_id = room_id;
    }
}
