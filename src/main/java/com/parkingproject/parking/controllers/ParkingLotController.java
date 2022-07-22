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

import com.parkingproject.parking.models.ParkingLot;
import com.parkingproject.parking.services.ParkingLotService;

@RestController
@RequestMapping("/parking-lot")
public class ParkingLotController {
    
    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping("/list")
    public ResponseEntity<List<ParkingLot>> getParkingLots() {
        try {
            List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
            parkingLots = parkingLotService.getAllParkingLots();
            if (parkingLots.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(parkingLots, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ParkingLot> getParkingLotById(int id) {
        Optional<ParkingLot> existingParkingLots = parkingLotService.getParkingLotById(id);
        if (existingParkingLots.isPresent()) {
            return new ResponseEntity<>(existingParkingLots.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ParkingLot> saveParkingLot(@RequestBody ParkingLot parkingLot) {
        try {
            ParkingLot _parkingLot = parkingLotService
                    .saveParkingLot(new ParkingLot(
                        parkingLot.getState(),
                        parkingLot.getType(),
                        parkingLot.getPrice(),
                        parkingLot.getParkingId()
                        ));
            return new ResponseEntity<>(_parkingLot, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ParkingLot> updateParkingLots(@PathVariable("id") int id, @RequestBody ParkingLot parkingLot) {
        Optional<ParkingLot> existingParkingLot = parkingLotService.getParkingLotById(id);
        if (existingParkingLot.isPresent()) {
            ParkingLot _parkingLot = existingParkingLot.get();
            _parkingLot.setState(parkingLot.getState());
            _parkingLot.setType(parkingLot.getType());
            _parkingLot.setPrice(parkingLot.getPrice());
            _parkingLot.setParkingId(parkingLot.getParkingId());
            return new ResponseEntity<>(parkingLotService.saveParkingLot(_parkingLot), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteParkingLotById(@PathVariable("id") int id) {
        try {
            parkingLotService.deleteParkingLotById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllParkingLots() {
        try {
            parkingLotService.deleteAllParkingLots();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

}
