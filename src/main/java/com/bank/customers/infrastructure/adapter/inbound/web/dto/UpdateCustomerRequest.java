package com.bank.customers.infrastructure.adapter.inbound.web.dto;

public record UpdateCustomerRequest(String id, String firstName,String lastName, String email) {
}
