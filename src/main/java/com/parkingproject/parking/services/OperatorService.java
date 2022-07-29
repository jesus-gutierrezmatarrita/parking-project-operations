package com.parkingproject.parking.services;

import com.parkingproject.parking.models.Operator;
import com.parkingproject.parking.repositories.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatorService {
    
    @Autowired
    private OperatorRepository operatorRepository;


    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }


    public Optional<Operator> getOperatorById(int id) {
        return operatorRepository.findById(id);
    }


    public Operator saveOperator(Operator operator) {
        return operatorRepository.save(operator);
	}


    public boolean deleteOperator(int id) {
        boolean result = false;
        try {
            operatorRepository.deleteById(id);
            result = true;
        } catch(Exception e) {

        }
        return result;
    }


    public boolean deleteAllOperators() {
        boolean result = false;
        try {
            operatorRepository.deleteAll();;
            result = true;
        } catch(Exception e) {
        }
        return result;
    }
}
