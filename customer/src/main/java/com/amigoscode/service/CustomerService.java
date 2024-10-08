package com.amigoscode.service;

import com.amigoscode.controller.CustomerRegistrationRequest;
import com.amigoscode.dto.FraudCheckResponse;
import com.amigoscode.model.Customer;
import com.amigoscode.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //TODO: check if email valid
        //todo: check if email not taken
        repository.saveAndFlush(customer);
        //todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        //todo: send notification
    }

}
