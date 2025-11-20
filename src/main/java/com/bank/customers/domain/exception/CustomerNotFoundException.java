package com.bank.customers.domain.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super("Customer not found with id : " +message);
    }
}
