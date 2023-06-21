package com.joey.ars.accomodationreservationservice.repository;

import com.joey.ars.accomodationreservationservice.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
