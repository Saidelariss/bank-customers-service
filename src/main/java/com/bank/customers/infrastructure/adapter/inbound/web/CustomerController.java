package com.bank.customers.infrastructure.adapter.inbound.web;

import com.bank.customers.application.port.inbound.*;
import com.bank.customers.domain.model.Customer;
import com.bank.customers.domain.model.CustomerId;
import com.bank.customers.infrastructure.adapter.inbound.web.dto.CreateCustomerRequest;
import com.bank.customers.infrastructure.adapter.inbound.web.dto.CustomerResponse;
import com.bank.customers.infrastructure.adapter.inbound.web.dto.UpdateCustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final CreateCustomerUseCase createCustomer;
    private final GetCustomerUseCase getCustomer;
    private final GetCustomersUseCase getCustomers;
    private final UpdateCustomerUseCase updateCustomer;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, UUID>> create(@RequestBody CreateCustomerRequest request) {
        CustomerId customerId = createCustomer.create(request.firstName(), request.lastName(), request.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", customerId.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable UUID id) {
        return getCustomer.getById(new CustomerId(id))
                .map(c -> ResponseEntity.ok(new CustomerResponse(c.id().value(), c.firstName(), c.lastName(), c.email(), c.createdAt())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public List<CustomerResponse> getCustomers() {
        return getCustomers.getAll()
                .stream()
                .map(customer ->
                        new CustomerResponse(customer.id().value(),
                                customer.firstName(),
                                customer.lastName(),
                                customer.email(),
                                customer.createdAt()))
                .toList();

    }

    @PutMapping
    public CustomerResponse updateCustomer(@RequestBody UpdateCustomerRequest request) {
        Customer customer = updateCustomer.update(new CustomerId(UUID.fromString(request.id())),
                request.firstName(),
                request.lastName(),
                request.email());

        return new CustomerResponse(customer.id().value(), customer.firstName(), customer.lastName(), customer.email(), customer.createdAt());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id){
        deleteCustomerUseCase.delete(new CustomerId(UUID.fromString(id)));
        return ResponseEntity.ok("User deleted successfully");

    }
}
