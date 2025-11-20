package com.bank.customers.config;

import com.bank.customers.application.port.inbound.CreateCustomerUseCase;
import com.bank.customers.application.port.inbound.GetCustomerUseCase;
import com.bank.customers.application.port.inbound.GetCustomersUseCase;
import com.bank.customers.application.port.inbound.UpdateCustomerUseCase;
import com.bank.customers.application.port.outbound.LoadCustomerPort;
import com.bank.customers.application.port.outbound.LoadCustomersPort;
import com.bank.customers.application.port.outbound.SaveCustomerPort;
import com.bank.customers.application.service.CreateCustomerService;
import com.bank.customers.application.service.GetCustomerService;
import com.bank.customers.application.service.GetCustomersService;
import com.bank.customers.application.service.UpdateCustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomersConfig {
    @Bean
    CreateCustomerUseCase createCustomerUseCase(SaveCustomerPort saver) {
        return new CreateCustomerService(saver);
    }

    @Bean
    GetCustomerUseCase getCustomerUseCase(LoadCustomerPort loadCustomerPort) {
        return new GetCustomerService(loadCustomerPort);
    }

    @Bean
    GetCustomersUseCase getCustomersUseCase(LoadCustomersPort loadCustomersPort) {
        return new GetCustomersService(loadCustomersPort);
    }

    @Bean
    UpdateCustomerUseCase updateCustomerUseCase(SaveCustomerPort saveCustomerPort,LoadCustomerPort loadCustomerPort){
        return new UpdateCustomerService(saveCustomerPort,loadCustomerPort);
    }
}
