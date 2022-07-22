package com.parkingproject.parking.controllers;

import com.parkingproject.parking.models.Parking;
import com.parkingproject.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/list")
    public ResponseEntity<List<Parking>> getParkings() {
        try {
            List<Parking> parking = new ArrayList<Parking>();
            parking = parkingService.getAllParking();
            if (parking.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(parking, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Parking> getParkingById(int id) {
        Optional<Parking> existingParking = parkingService.getParkingById(id);
        if (existingParking.isPresent()) {
            return new ResponseEntity<>(existingParking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Parking> saveParkingLot(@RequestBody Parking parking) {
        try {
            Parking _parking = parkingService
                    .saveParking(new Parking(
                        parking.getName(),
                        parking.getLocation(),
                        parking.getCapacity()
                        ));
            return new ResponseEntity<>(_parking, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Parking> updateParkingLots(@PathVariable("id") int id, @RequestBody Parking parking) {
        Optional<Parking> existingParking = parkingService.getParkingById(id);
        if (existingParking.isPresent()) {
            Parking _parking = existingParking.get();
            _parking.setName(parking.getName());
            _parking.setLocation(parking.getLocation());
            _parking.setCapacity(parking.getCapacity());
            return new ResponseEntity<>(parkingService.saveParking(_parking), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteParkingById(@PathVariable("id") int id) {
        try {
            parkingService.deleteParkingById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllParkings() {
        try {
            parkingService.deleteAllParking();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

}
