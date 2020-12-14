package de.unikassel.soc.platform.services;

import de.unikassel.soc.platform.domain.Customer;
import de.unikassel.soc.platform.repos.CustomerRepo;
import de.unikassel.soc.platform.web.mappers.CustomerMapper;
import de.unikassel.soc.platform.web.mappers.CustomerMapperImpl;
import de.unikassel.soc.platform.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo repo;
    private CustomerMapper mapper = new CustomerMapperImpl();

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        Customer customer = repo.findById(customerId).get();
        return mapper.customerToCustomerDto(customer);
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        Customer customer = mapper.customerDtoToCustomer(customerDto);
        repo.save(customer);
        return customerDto;
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        Customer customer = repo.findById(customerId).get();
        customer.setName(customerDto.getName());
    }

    @Override
    public void deleteById(UUID customerId) {
        repo.deleteById(customerId);
    }

    @Override
    public CustomerDto getCustomerByName(String customerName) {
        List<Customer> customers = repo.findByName(customerName);
        if(customers.size()>0) {
            return mapper.customerToCustomerDto(customers.get(0));
        }
        return null;
    }
}
