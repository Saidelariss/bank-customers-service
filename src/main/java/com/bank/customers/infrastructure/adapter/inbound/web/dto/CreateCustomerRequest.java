package com.bank.customers.infrastructure.adapter.inbound.web.dto;

public record CreateCustomerRequest(String firstName,String lastName, String email) {
}
