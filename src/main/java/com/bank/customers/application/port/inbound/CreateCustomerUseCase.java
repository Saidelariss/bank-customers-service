package com.bank.customers.application.port.inbound;

import com.bank.customers.domain.model.CustomerId;

public interface CreateCustomerUseCase {
    CustomerId create(String firstName,String lastName,String email);
}
