package com.bank.customers.adapter.inbound.web;

import com.bank.customers.adapter.inbound.web.dto.CreateCustomerRequest;
import com.bank.customers.adapter.inbound.web.dto.CustomerResponse;
import com.bank.customers.application.port.inbound.CreateCustomerUseCase;
import com.bank.customers.application.port.inbound.GetCustomerUseCase;
import com.bank.customers.domain.model.CustomerId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CreateCustomerUseCase createCustomer;
    private final GetCustomerUseCase getCustomer;

    public Map<String, UUID> create(@RequestBody CreateCustomerRequest request){
        CustomerId customerId = createCustomer.create(request.firstName(), request.lastName(), request.email());
        return Map.of("id",customerId.value());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable UUID id){
        return getCustomer.getById(new CustomerId(id))
                .map(c -> ResponseEntity.ok(new CustomerResponse(c.id().value(), c.firstName(), c.lastName(), c.email(), c.createdAt())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
