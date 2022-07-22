package com.parkingproject.parking.services;

import com.parkingproject.parking.models.Customer;
import com.parkingproject.parking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }


    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
	}


    public boolean deleteCustomer(int id) {
        boolean result = false;
        try {
            customerRepository.deleteById(id);
            result = true;
        } catch(Exception e) {

        }
        return result;
    }


    public boolean deleteAllCustomers() {
        boolean result = false;
        try {
            customerRepository.deleteAll();;
            result = true;
        } catch(Exception e) {
        }
        return result;
    }
}
