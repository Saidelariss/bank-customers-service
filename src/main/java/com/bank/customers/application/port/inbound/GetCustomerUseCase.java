package com.bank.customers.application.port.inbound;

import com.bank.customers.domain.model.Customer;
import com.bank.customers.domain.model.CustomerId;

import java.util.Optional;

public interface GetCustomerUseCase {
    Optional<Customer> getById(CustomerId id);
}
