package de.unikassel.soc.platform.services;

import de.unikassel.soc.platform.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteById(UUID customerId);

    CustomerDto getCustomerByName(String customerName);
}
