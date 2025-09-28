package com.bank.customers.config;

import com.bank.customers.application.port.inbound.CreateCustomerUseCase;
import com.bank.customers.application.port.inbound.GetCustomerUseCase;
import com.bank.customers.application.port.outbound.LoadCustomerPort;
import com.bank.customers.application.port.outbound.SaveCustomerPort;
import com.bank.customers.application.service.CreateCustomerService;
import com.bank.customers.application.service.GetCustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomersConfig {
    @Bean
    CreateCustomerUseCase createCustomerUseCase(SaveCustomerPort saver){
        return new CreateCustomerService(saver);
    }

    @Bean
    GetCustomerUseCase getCustomerUseCase(LoadCustomerPort loader){
        return new GetCustomerService(loader);
    }
}
