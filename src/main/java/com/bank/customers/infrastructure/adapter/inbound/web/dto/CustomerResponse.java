package com.bank.customers.infrastructure.adapter.inbound.web.dto;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponse(UUID id, String firstName, String lastName, String email, Instant createdAt) {
}
