package com.amigoscode.service;

import com.amigoscode.controller.CustomerRegistrationRequest;
import com.amigoscode.model.Customer;
import com.amigoscode.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //TODO: check if email valid
        //todo: check if email not taken

        repository.save(customer);
    }
}
