package com.parkingproject.parking.services;

import com.parkingproject.parking.models.Administrator;
import com.parkingproject.parking.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    
    @Autowired
    private AdministratorRepository administratorRepository;


    public List<Administrator> getAllAdministrators() {
        return administratorRepository.findAll();
    }


    public Optional<Administrator> getAdministratorById(int id) {
        return administratorRepository.findById(id);
    }


    public Administrator saveAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
	}


    public boolean deleteAdmistrador(int id) {
        boolean result = false;
        try {
            administratorRepository.deleteById(id);
            result = true;
        } catch(Exception e) {

        }
        return result;
    }


    public boolean deleteAllAdministrators() {
        boolean result = false;
        try {
            administratorRepository.deleteAll();;
            result = true;
        } catch(Exception e) {
        }
        return result;
    }
}
