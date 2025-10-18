package com.bank.customers.application.service;

import com.bank.customers.application.port.inbound.GetCustomersUseCase;
import com.bank.customers.application.port.outbound.LoadCustomersPort;
import com.bank.customers.domain.model.Customer;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetCustomersService implements GetCustomersUseCase {
    private final LoadCustomersPort loadCustomersPort;
    @Override
    public List<Customer> getAll() {
        return loadCustomersPort.loadAll();
    }
}
