package com.example.videostore.controller;

import com.example.videostore.exception.ResourceNotFoundException;
import com.example.videostore.model.Customer;
import com.example.videostore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer, BindingResult result) {
        if (customer.getEmail() == null || !customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return new ResponseEntity<>("Email is not valid.", HttpStatus.BAD_REQUEST);
        }
        if (customer.isEmpty()) { // Implement the isEmpty method in Customer.
            return new ResponseEntity<>("Customer details are empty", HttpStatus.BAD_REQUEST);
        }
        String password = customer.getPassword();
        if (password == null || password.trim().isEmpty() || password.length() < 6) {
            return new ResponseEntity<>("Password is not valid. It must be at least 6 characters long.", HttpStatus.BAD_REQUEST);
        }
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            result.getFieldErrors().forEach(err -> errorMap.put(err.getField(), err.getDefaultMessage()));
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Customer registeredCustomer = customerService.registerCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Customer registered successfully", "customer", registeredCustomer));
    }


    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable String customerId) {
        try {
            Customer customer = customerService.getCustomerById(customerId);
            return ResponseEntity.ok(customer);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> authRequest) {
        String email = authRequest.get("email");
        String password = authRequest.get("password");

        // Perform authentication
        Customer customer = customerService.authenticateCustomer(email, password);

        if (customer != null) {
            // Authentication successful, include customer details in the response
            return ResponseEntity.ok(Map.of("message", "Authentication successful", "customer", customer));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Authentication failed"));
        }
    }

}
