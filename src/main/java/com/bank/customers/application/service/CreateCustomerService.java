package com.bank.customers.application.service;

import com.bank.customers.application.port.inbound.CreateCustomerUseCase;
import com.bank.customers.application.port.outbound.SaveCustomerPort;
import com.bank.customers.domain.model.Customer;
import com.bank.customers.domain.model.CustomerId;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class CreateCustomerService implements CreateCustomerUseCase {
    private final SaveCustomerPort saver;
    @Override
    public CustomerId create(String firstName, String lastName, String email) {
        if(saver.emailExists(email)) throw new IllegalStateException("email already exists");
        var customer = new Customer(new CustomerId(UUID.randomUUID()),firstName,lastName,email,null);
        return saver.save(customer).id();
    }
}
