package com.joey.ars.accomodationreservationservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "roomImage")
public class RoomImage { // image로 변경
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
