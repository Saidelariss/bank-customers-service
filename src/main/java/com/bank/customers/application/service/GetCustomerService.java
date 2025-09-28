package com.bank.customers.application.service;

import com.bank.customers.application.port.inbound.GetCustomerUseCase;
import com.bank.customers.application.port.outbound.LoadCustomerPort;
import com.bank.customers.domain.model.Customer;
import com.bank.customers.domain.model.CustomerId;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class GetCustomerService implements GetCustomerUseCase {
    private final LoadCustomerPort loader;
    @Override
    public Optional<Customer> getById(CustomerId id) {
        return loader.loadById(id);
    }
}
