package com.parkingproject.parking.controllers;

import com.parkingproject.parking.models.Administrator;
import com.parkingproject.parking.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;
    private List<Administrator> listAdministrator;

    @GetMapping("/list")
    public ResponseEntity<List<Administrator>> getAdministrator() {
        try {
            List<Administrator> administrators = new ArrayList<Administrator>();
            administrators = administratorService.getAllAdministrators();
            if (administrators.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(administrators, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Administrator> getAdministratorById(int id) {
        Optional<Administrator> existingAdmin = administratorService.getAdministratorById(id);
        if (existingAdmin.isPresent()) {
            return new ResponseEntity<>(existingAdmin.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Administrator> saveAdministrator(@RequestBody Administrator administrator) {
        try {
            Administrator _administrator = administratorService
                    .saveAdministrator(new Administrator(administrator.getName(), administrator.getLastname(), administrator.getPassword(), administrator.getEmail(), administrator.getPhone()));
            return new ResponseEntity<>(_administrator, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable("id") int id, @RequestBody Administrator administrator) {
        Optional<Administrator> existingAdmin = administratorService.getAdministratorById(id);
        if (existingAdmin.isPresent()) {
            Administrator _admin = existingAdmin.get();
            _admin.setName(administrator.getName());
            _admin.setLastname(administrator.getLastname());
            _admin.setPassword(administrator.getPassword());
            _admin.setEmail(administrator.getEmail());
            _admin.setPhone(administrator.getPhone());
            return new ResponseEntity<>(administratorService.saveAdministrator(_admin), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAdministratorById(@PathVariable("id") int id) {
        try {
            administratorService.deleteAdmistrador(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllAdministrators() {
        try {
            administratorService.deleteAllAdministrators();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

}
