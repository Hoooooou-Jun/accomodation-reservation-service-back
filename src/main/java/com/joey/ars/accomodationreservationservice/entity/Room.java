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
    private Long id;

    @Column(length = 12, nullable = false)
    private String name;

    @Column(length = 8, nullable = false)
    private String type;

    @Column(length = 12, nullable = false)
    private String price;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String service;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String amenity;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();
}
