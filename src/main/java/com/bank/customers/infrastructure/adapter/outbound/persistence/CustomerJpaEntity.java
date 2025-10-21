package com.bank.customers.infrastructure.adapter.outbound.persistence;

import com.bank.customers.domain.model.Customer;
import com.bank.customers.domain.model.CustomerId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerJpaEntity {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Instant createdAt;


    public static CustomerJpaEntity toEntity(Customer customer) {
        return new CustomerJpaEntity(customer.id().value(), customer.firstName(), customer.lastName(), customer.email(), customer.createdAt());
    }

    public Customer toDomain() {
        return new Customer(new CustomerId(this.getId()), this.getFirstName(), this.getLastName(), this.getEmail(), this.getCreatedAt());
    }
}
