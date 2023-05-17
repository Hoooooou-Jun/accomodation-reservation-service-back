package com.joey.ars.accomodationreservationservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer roomId;

    @Column(length = 12, nullable = false)
    private String roomname;

    @Column(length = 8, nullable = false)
    private String type;

    @Column(length = 12, nullable = false)
    private String price;

    @Column(length = 128, nullable = false)
    private String info;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();
}
