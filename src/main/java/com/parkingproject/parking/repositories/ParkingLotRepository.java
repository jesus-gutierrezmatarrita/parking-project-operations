package com.parkingproject.parking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkingproject.parking.models.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {
    
}
