package com.bank.customers.application.port.outbound;

import com.bank.customers.domain.model.Customer;
import com.bank.customers.domain.model.CustomerId;

import java.util.Optional;

public interface LoadCustomerPort {
    Optional<Customer> loadById(CustomerId id);
}
