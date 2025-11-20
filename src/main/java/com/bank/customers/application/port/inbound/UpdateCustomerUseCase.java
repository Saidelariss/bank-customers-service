package com.bank.customers.application.port.inbound;

import com.bank.customers.domain.model.Customer;
import com.bank.customers.domain.model.CustomerId;

public interface UpdateCustomerUseCase {
    Customer update(CustomerId customerId, String firstName, String lastName, String email);
}
