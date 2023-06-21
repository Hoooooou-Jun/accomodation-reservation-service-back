package com.joey.ars.accomodationreservationservice.repository;

import com.joey.ars.accomodationreservationservice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMember_id(Long member_id);
}
