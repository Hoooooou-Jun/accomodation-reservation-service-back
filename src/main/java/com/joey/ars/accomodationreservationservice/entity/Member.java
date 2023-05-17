package com.joey.ars.accomodationreservationservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Id
    @Column(length = 48, nullable = false)
    private String email;

    @Column(length = 28, nullable = false)
    private String password;

    @Column(length = 12, nullable = false)
    private String username;

    @Column(length = 14, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations  = new ArrayList<>();
}
