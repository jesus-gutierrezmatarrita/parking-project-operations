package com.parkingproject.parking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkingproject.parking.models.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    
}
