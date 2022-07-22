package com.parkingproject.parking.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkingproject.parking.models.Fare;
import com.parkingproject.parking.services.FareService;

@RestController
@RequestMapping("/fare")
public class FareController {
    
    @Autowired
    private FareService fareService;

    @GetMapping("/list")
    public ResponseEntity<List<Fare>> getFares() {
        try {
            List<Fare> fares = new ArrayList<>();
            fares = fareService.getAllFares();
            if (fares.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(fares, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Fare> getFareById(int id) {
        Optional<Fare> existingFare = fareService.getFareById(id);
        if (existingFare.isPresent()) {
            return new ResponseEntity<>(existingFare.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Fare> saveFare(@RequestBody Fare fare) {
        try {
            Fare _fare = fareService
                    .saveFare(new Fare(
                        fare.getParkingSlotId(),
                        fare.getPrice(),
                        fare.getVehicleCategoryId(),
                        fare.getUnitTimeId()
                    ));
            return new ResponseEntity<>(_fare, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fare> updateFare(@PathVariable("id") int id, @RequestBody Fare fare) {
        Optional<Fare> existingFare = fareService.getFareById(id);
        if (existingFare.isPresent()) {
            Fare _fare = existingFare.get();
            _fare.setParkingSlotId(fare.getParkingSlotId());
            _fare.setPrice(fare.getPrice());
            _fare.setVehicleCategoryId(fare.getVehicleCategoryId());
            _fare.setUnitTimeId(fare.getUnitTimeId());
            return new ResponseEntity<>(fareService.saveFare(_fare), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteFareById(@PathVariable("id") int id) {
        try {
            fareService.deleteFare(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllFares() {
        try {
            fareService.deleteAllFares();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
  
}
