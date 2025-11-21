package com.bank.customers.application.service;

import com.bank.customers.application.port.inbound.GetCustomersUseCase;
import com.bank.customers.application.port.outbound.LoadCustomersPort;
import com.bank.customers.domain.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class GetCustomersService implements GetCustomersUseCase {
    private final LoadCustomersPort loadCustomersPort;

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return loadCustomersPort.loadAll(pageable);
    }
}
