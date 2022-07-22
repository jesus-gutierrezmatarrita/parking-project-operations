package com.parkingproject.parking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingproject.parking.models.ParkingLot;
import com.parkingproject.parking.repositories.ParkingLotRepository;

@Service
public class ParkingLotService {
    
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    public Optional<ParkingLot> getParkingLotById(int id) {
        return parkingLotRepository.findById(id);
    }

    /*
     * Save a parking lot
     */
    public ParkingLot saveParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
	}

    /*
     * Delete a parking lot by a given id
     */
    public boolean deleteParkingLotById(int id) {
        boolean result = false;
        try {
            parkingLotRepository.deleteById(id);
            result = true;
        } catch(Exception e) {

        }
        return result;
    }

    /*
     * Delete all parking lot
     */
    public boolean deleteAllParkingLots() {
        boolean result = false;
        try {
            parkingLotRepository.deleteAll();
            result = true;
        } catch(Exception e) {

        }
        return result;
    }
}
