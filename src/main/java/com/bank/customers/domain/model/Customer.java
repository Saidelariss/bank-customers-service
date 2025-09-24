package com.bank.customers.domain.model;

import java.time.Instant;

public class Customer {
    private final CustomerId id;
    private String firstName;
    private String lastName;
    private String email;
    private Instant createdAt;

    public Customer(CustomerId id, String firstName, String lastName, String email, Instant createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
    }

    public CustomerId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
