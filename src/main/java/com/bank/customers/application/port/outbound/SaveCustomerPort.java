package com.bank.customers.application.port.outbound;

import com.bank.customers.domain.model.Customer;

public interface SaveCustomerPort {
    Customer save(Customer customer);
    boolean emailExists(String email);
}
