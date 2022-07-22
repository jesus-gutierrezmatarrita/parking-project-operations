package com.parkingproject.parking.services;

import com.parkingproject.parking.models.Parking;
import com.parkingproject.parking.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {
    
    @Autowired
    private ParkingRepository parkingRepository;

    public List<Parking> getAllParking() {
        return parkingRepository.findAll();
    }

    public Optional<Parking> getParkingById(int id) {
        return parkingRepository.findById(id);
    }


    public Parking saveParking(Parking parking) {
        return parkingRepository.save(parking);
	}

    /*
     * Delete a parking lot by a given id
     */
    public boolean deleteParkingById(int id) {
        boolean result = false;
        try {
            parkingRepository.deleteById(id);
            result = true;
        } catch(Exception e) {

        }
        return result;
    }


    public boolean deleteAllParking() {
        boolean result = false;
        try {
            parkingRepository.deleteAll();
            result = true;
        } catch(Exception e) {

        }
        return result;
    }
}
