package com.parkingproject.parking.services;

import com.parkingproject.parking.models.Role;
import com.parkingproject.parking.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /*
     * Get a role by a given id. It could be null, so Optional method handles it even so
     */
    public Optional<Role> getRoleById(int id) {
        return roleRepository.findById(id);
    }

    /*
     * Save a role
     */
    public Role saveRole(Role role) {
        return roleRepository.save(role);
	}

    /*
     * Delete a role by a given id
     */
    public boolean deleteRole(int id) {
        boolean result = false;
        try {
            roleRepository.deleteById(id);
            result = true;
        } catch(Exception e) {

        }
        return result;
    }


    public boolean deleteAllRoles() {
        boolean result = false;
        try {
            roleRepository.deleteAll();;
            result = true;
        } catch(Exception e) {

        }
        return result;
    }

}
