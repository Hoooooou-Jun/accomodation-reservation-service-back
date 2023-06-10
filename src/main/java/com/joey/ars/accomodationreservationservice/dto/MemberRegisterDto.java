package com.joey.ars.accomodationreservationservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberRegisterDto {
    private String email;
    private String password;
    private String username;
    private String phone;

}
