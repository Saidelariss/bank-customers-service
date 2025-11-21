package com.bank.customers.application.port.outbound;

import com.bank.customers.domain.model.CustomerId;

public interface DeleteCustomerPort {
    void deleteById(CustomerId id);
}
