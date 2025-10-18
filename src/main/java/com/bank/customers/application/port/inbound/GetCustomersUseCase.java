package com.bank.customers.application.port.inbound;

import com.bank.customers.domain.model.Customer;

import java.util.List;

public interface GetCustomersUseCase {
    List<Customer> getAll();
}
