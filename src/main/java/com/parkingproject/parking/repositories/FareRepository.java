package com.parkingproject.parking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkingproject.parking.models.Fare;

@Repository
public interface FareRepository extends JpaRepository<Fare, Integer> {
    
}
