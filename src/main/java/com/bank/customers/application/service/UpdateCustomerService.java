package com.bank.customers.application.service;

import com.bank.customers.application.port.inbound.UpdateCustomerUseCase;
import com.bank.customers.application.port.outbound.LoadCustomerPort;
import com.bank.customers.application.port.outbound.SaveCustomerPort;
import com.bank.customers.domain.exception.CustomerNotFoundException;
import com.bank.customers.domain.model.Customer;
import com.bank.customers.domain.model.CustomerId;
import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class UpdateCustomerService implements UpdateCustomerUseCase {
    private final SaveCustomerPort saveCustomerPort;
    private final LoadCustomerPort loadCustomerPort;

    @Override
    public Customer update(CustomerId customerId, String firstName, String lastName, String email) {
        loadCustomerPort.loadById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId.value().toString()));

        Customer updatedCustomer = new Customer(customerId,firstName,lastName,email, Instant.now());
        return saveCustomerPort.save(updatedCustomer);
    }
}
