package com.bank.customers.application.port.outbound;

import com.bank.customers.domain.model.Customer;

import java.util.List;

public interface LoadCustomersPort {
    List<Customer> loadAll();
}
