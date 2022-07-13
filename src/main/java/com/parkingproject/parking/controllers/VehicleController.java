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

import com.parkingproject.parking.models.Vehicle;
import com.parkingproject.parking.services.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    private List<Vehicle> listVehicles;

    @GetMapping("/list")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        try {
            List<Vehicle> vehicles = new ArrayList<Vehicle>();
            vehicles = vehicleService.getAllVehicles();
            if (vehicles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Vehicle> getVehicleById(int id) {
        Optional<Vehicle> existingVehicle = vehicleService.getVehicleById(id);
        if (existingVehicle.isPresent()) {
            return new ResponseEntity<>(existingVehicle.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle _vehicle = vehicleService
                    .saveVehicle(new Vehicle(vehicle.getLicensePlate(), vehicle.getCarBrand(), vehicle.getCarModel(),
                            vehicle.getColor(), vehicle.getCategoryId()));
            return new ResponseEntity<>(_vehicle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle vehicle) {
        Optional<Vehicle> existingVehicle = vehicleService.getVehicleById(id);
        if (existingVehicle.isPresent()) {
            Vehicle _vehicle = existingVehicle.get();
            _vehicle.setLicensePlate(vehicle.getLicensePlate());
            _vehicle.setCarBrand(vehicle.getCarBrand());
            _vehicle.setCarModel(vehicle.getCarModel());
            _vehicle.setColor(vehicle.getColor());
            _vehicle.setCategoryId(vehicle.getCategoryId());
            return new ResponseEntity<>(vehicleService.saveVehicle(_vehicle), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteVehicleById(@PathVariable("id") int id) {
        try {
            vehicleService.deleteVehicle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllVehicles() {
        try {
            vehicleService.deleteAllVehicles();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

}
