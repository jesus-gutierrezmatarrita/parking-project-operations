package com.parkingproject.parking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingproject.parking.models.Fare;
import com.parkingproject.parking.repositories.FareRepository;

@Service
public class FareService {

    @Autowired
    private FareRepository fareRepository;

    /**
     * Get all fares from the database
     */
    public List<Fare> getAllFares() {
        return fareRepository.findAll();
    }

    /*
     * Get a fare by a given id. It could be null, so Optional method handles it even so
     */
    public Optional<Fare> getFareById(int id) {
        return fareRepository.findById(id);
    }

    /*
     * Save a fare
     */
    public Fare saveFare(Fare fare) {
        return fareRepository.save(fare);
	}

    /*
     * Delete a fare by a given id
     */
    public boolean deleteFare(int id) {
        boolean result = false;
        try {
            fareRepository.deleteById(id);
            result = true;
        } catch(Exception e) {

        }
        return result;
    }

    /*
     * Delete all fares
     */
    public boolean deleteAllFares() {
        boolean result = false;
        try {
            fareRepository.deleteAll();;
            result = true;
        } catch(Exception e) {

        }
        return result;
    }

    
}
