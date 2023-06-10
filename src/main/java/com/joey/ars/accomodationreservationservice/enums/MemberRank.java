package com.joey.ars.accomodationreservationservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRank {
    GUEST("ROLE_GUEST"), USER("ROLE_USER");
    private final String key;
}
