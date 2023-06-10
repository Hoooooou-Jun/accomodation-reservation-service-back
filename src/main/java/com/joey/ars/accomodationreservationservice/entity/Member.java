package com.joey.ars.accomodationreservationservice.entity;

import com.joey.ars.accomodationreservationservice.enums.MemberRank;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member")
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private MemberRank rank; // enum 타입 변경(userRoll)

    private boolean deleted;

    private String refreshToken;

    public void authorizeMember() {
        this.rank = MemberRank.USER;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    @OneToMany(mappedBy = "member")
    private List<Question> questions;

    @OneToMany(mappedBy = "member") // reservation이 연관관계 주인이므로 mappedBy 선언
    private List<Reservation> reservations  = new ArrayList<>();
}
