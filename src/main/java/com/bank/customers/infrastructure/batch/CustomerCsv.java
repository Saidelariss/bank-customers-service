package com.bank.customers.infrastructure.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerCsv {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String createdAt;
}
