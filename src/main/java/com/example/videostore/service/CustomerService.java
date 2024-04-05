package com.example.videostore.service;

import com.example.videostore.model.Customer;

public interface CustomerService {
    Customer registerCustomer(Customer customer);
    Customer getCustomerById(String customerId);
    //boolean authenticateUser(String email, String password);
    Customer authenticateCustomer(String email, String password); // Add this method

}
