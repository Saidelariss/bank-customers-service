package com.bank.customers.domain.model;

import java.time.Instant;

public record Customer(CustomerId id,String firstName,String lastName, String email, Instant createdAt) {
}
