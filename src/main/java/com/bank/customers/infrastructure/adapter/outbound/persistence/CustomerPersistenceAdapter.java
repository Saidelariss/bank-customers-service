package com.bank.customers.infrastructure.adapter.outbound.persistence;

import com.bank.customers.application.port.outbound.DeleteCustomerPort;
import com.bank.customers.application.port.outbound.LoadCustomerPort;
import com.bank.customers.application.port.outbound.LoadCustomersPort;
import com.bank.customers.application.port.outbound.SaveCustomerPort;
import com.bank.customers.domain.model.Customer;
import com.bank.customers.domain.model.CustomerId;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomerPersistenceAdapter implements SaveCustomerPort, LoadCustomerPort, LoadCustomersPort, DeleteCustomerPort {
    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public Optional<Customer> loadById(CustomerId id) {
        Optional<CustomerJpaEntity> customerJpaEntity = customerJpaRepository.findById(id.value());
        return customerJpaEntity.map(CustomerJpaEntity::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity customerJpaEntity = customerJpaRepository.save(CustomerJpaEntity.toEntity(customer));
        return customerJpaEntity.toDomain();
    }

    @Override
    public boolean emailExists(String email) {
        return customerJpaRepository.findByEmailIgnoreCase(email).isPresent();
    }

    @Override
    public Page<Customer> loadAll(Pageable pageable) {
        return customerJpaRepository.findAll(pageable).map(CustomerJpaEntity::toDomain);
    }

    @Override
    public void deleteById(CustomerId id) {
        customerJpaRepository.deleteById(id.value());
    }
}
