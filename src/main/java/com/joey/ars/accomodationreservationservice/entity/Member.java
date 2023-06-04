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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 48, unique = true)
    private String email;

    @Column(length = 28, nullable = false)
    private String password;

    @Column(length = 12, nullable = false)
    private String username;

    @Column(length = 14)
    private String phone;

    @Column(length = 8)
    private String rank; // enum 타입 변경(userRoll)

    private boolean deleted;

    @OneToMany(mappedBy = "member")
    private List<Question> questions;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations  = new ArrayList<>();
}
