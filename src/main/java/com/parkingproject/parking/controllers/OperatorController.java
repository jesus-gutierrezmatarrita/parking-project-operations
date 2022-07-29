package com.parkingproject.parking.controllers;

import com.parkingproject.parking.models.Operator;
import com.parkingproject.parking.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;
    private List<Operator> listOperator;

    @GetMapping("/list")
    public ResponseEntity<List<Operator>> getOperator() {
        try {
            List<Operator> operators = new ArrayList<Operator>();
            operators = operatorService.getAllOperators();
            if (operators.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(operators, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Operator> getOperatorById(int id) {
        Optional<Operator> existingOperator = operatorService.getOperatorById(id);
        if (existingOperator.isPresent()) {
            return new ResponseEntity<>(existingOperator.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Operator> saveOperator(@RequestBody Operator operator) {
        try {
            Operator _operator = operatorService
                    .saveOperator(new Operator(operator.getName(), operator.getLastname(), operator.getPassword(), operator.getEmail(), operator.getPhone()));
            return new ResponseEntity<>(_operator, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Operator> updateOperator(@PathVariable("id") int id, @RequestBody Operator operator) {
        Optional<Operator> existingOperator = operatorService.getOperatorById(id);
        if (existingOperator.isPresent()) {
            Operator _operator = existingOperator.get();
            _operator.setName(operator.getName());
            _operator.setLastname(operator.getLastname());
            _operator.setPassword(operator.getPassword());
            _operator.setEmail(operator.getEmail());
            _operator.setPhone(operator.getPhone());
            return new ResponseEntity<>(operatorService.saveOperator(_operator), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteOperatorById(@PathVariable("id") int id) {
        try {
            operatorService.deleteOperator(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllOoperators() {
        try {
            operatorService.deleteAllOperators();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

}
