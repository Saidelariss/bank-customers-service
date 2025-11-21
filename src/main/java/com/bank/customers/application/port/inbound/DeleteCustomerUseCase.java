package com.bank.customers.application.port.inbound;

import com.bank.customers.domain.model.CustomerId;

public interface DeleteCustomerUseCase {
    void delete(CustomerId customerId);
}
