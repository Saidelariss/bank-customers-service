package com.bank.customers.infrastructure.batch;

public record CustomerCsv(String id,
                          String firstName,
                          String lastName,
                          String email,
                          String status,
                          String createdAt) {
}
