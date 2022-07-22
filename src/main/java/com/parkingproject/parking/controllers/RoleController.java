package com.parkingproject.parking.controllers;

import com.parkingproject.parking.models.Role;
import com.parkingproject.parking.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    private List<Role> listRole;

    @GetMapping("/list")
    public ResponseEntity<List<Role>> getRole() {
        try {
            List<Role> vehicles = new ArrayList<Role>();
            vehicles = roleService.getAllRoles();
            if (vehicles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Role> getRoleById(int id) {
        Optional<Role> existingVehicle = roleService.getRoleById(id);
        if (existingVehicle.isPresent()) {
            return new ResponseEntity<>(existingVehicle.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        try {
            Role _role = roleService
                    .saveRole(new Role(role.getName()));
            return new ResponseEntity<>(_role, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") int id, @RequestBody Role role) {
        Optional<Role> existingRole = roleService.getRoleById(id);
        if (existingRole.isPresent()) {
            Role _role = existingRole.get();
            _role.setName(role.getName());
            return new ResponseEntity<>(roleService.saveRole(_role), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteRoleById(@PathVariable("id") int id) {
        try {
            roleService.deleteRole(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllRoles() {
        try {
            roleService.deleteAllRoles();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

}
