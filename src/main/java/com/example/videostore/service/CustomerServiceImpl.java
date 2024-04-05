package com.example.videostore.service;

import com.example.videostore.exception.ResourceNotFoundException;
import com.example.videostore.model.Customer;
import com.example.videostore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Customer registerCustomer(Customer customer) {
        // Encrypt password before saving
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
    }

    @Override
    public Customer authenticateCustomer(String email, String password) {
        // Find customer by email
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            // Check if provided password matches the encrypted password
            if (bCryptPasswordEncoder.matches(password, customer.getPassword())) {
                // Return the authenticated customer object
                return customer;
            }
        }
        // Return null if authentication fails
        return null;
    }

}
