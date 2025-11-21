package com.bank.customers.application.port.inbound;

import com.bank.customers.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetCustomersUseCase {
    Page<Customer> getAll(Pageable pageable);
}
