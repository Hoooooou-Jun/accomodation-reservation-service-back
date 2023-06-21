package com.joey.ars.accomodationreservationservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;

    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Builder
    public Reservation(LocalDate start_date, LocalDate end_date, Boolean deleted, Member member_id, Room room_id) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.deleted = deleted;
        this.member = member_id;
        this.room = room_id;
    }
}
