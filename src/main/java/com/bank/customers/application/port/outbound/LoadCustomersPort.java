package com.bank.customers.application.port.outbound;

import com.bank.customers.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadCustomersPort {
    Page<Customer> loadAll(Pageable pageable);
}
