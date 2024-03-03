package com.widgets_are_us.customers_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgets_are_us.customers_service.dto.CustomerDto;
import com.widgets_are_us.customers_service.exceptions.ResourceNotFoundException;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class CustomerService {

    public static final String CUSTOMER_NOT_FOUND_FOR_THIS_ID =
            "Customer not found for this id: [%s]";

    private static final ObjectMapper mapper = new ObjectMapper();

    CustomerRepository customerRepository;

    public Customer createCustomer(String json) {

        log.info("Mapping customer from json: " + json);
        Customer customer = fromJson(json);
        customer = customerRepository.save(customer);
        log.debug("Customer created: " + customer);

        return customer;
    }

    public Customer replaceCustomer(Long id, CustomerDto updatedCustomerDto) {

        customerRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        CUSTOMER_NOT_FOUND_FOR_THIS_ID.formatted(id)));

        Customer updatedCustomer = customerRepository.save(new Customer(updatedCustomerDto));
        log.debug(updatedCustomer.toJson());

        return updatedCustomer;
    }

    public Customer fromJson(String json) {
        try {
            log.info("Mapping customer from json");
            return new Customer(mapper.readValue(json, CustomerDto.class));
        } catch (Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return new Customer();
        }
    }
}
