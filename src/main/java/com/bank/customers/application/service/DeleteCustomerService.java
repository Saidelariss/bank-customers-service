package com.bank.customers.application.service;

import com.bank.customers.application.port.inbound.DeleteCustomerUseCase;
import com.bank.customers.application.port.outbound.DeleteCustomerPort;
import com.bank.customers.application.port.outbound.LoadCustomerPort;
import com.bank.customers.domain.exception.CustomerNotFoundException;
import com.bank.customers.domain.model.CustomerId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCustomerService implements DeleteCustomerUseCase {
    private final LoadCustomerPort loadCustomerPort;
    private final DeleteCustomerPort deleteCustomerPort;
    @Override
    public void delete(CustomerId customerId) {
        loadCustomerPort.loadById(customerId)
                .orElseThrow(()->new CustomerNotFoundException(customerId.value().toString()));

        deleteCustomerPort.deleteById(customerId);

    }
}
