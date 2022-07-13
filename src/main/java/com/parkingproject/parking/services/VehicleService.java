package com.parkingproject.parking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingproject.parking.models.Vehicle;
import com.parkingproject.parking.repositories.VehicleRepository;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;

    /*
     * Get all the vehicles from the database
     */
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    /*
     * Get a vehicle by a given id. It could be null, so Optional method handles it even so
     */
    public Optional<Vehicle> getVehicleById(int id) {
        return vehicleRepository.findById(id);
    }

    /*
     * Save a vehicle
     */
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
	}

    /*
     * Delete a vehicle by a given id
     */
    public boolean deleteVehicle(int id) {
        boolean result = false;
        try {
            vehicleRepository.deleteById(id);
            result = true;
        } catch(Exception e) {

        }
        return result;
    }

    /*
     * Delete all vehicles
     */
    public boolean deleteAllVehicles() {
        boolean result = false;
        try {
            vehicleRepository.deleteAll();;
            result = true;
        } catch(Exception e) {

        }
        return result;
    }

}
