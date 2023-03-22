package com.widgets_are_us.customers_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgets_are_us.customers_service.exceptions.ResourceNotFoundException;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.repositories.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CustomerService {

    public static final String CUSTOMER_NOT_FOUND_FOR_THIS_ID = "Customer not found for this id :: ";

    private static final ObjectMapper mapper = new ObjectMapper();

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(String json) {

        log.info("Mapping customer from json: " + json);
        Customer customer = fromJson(json);
        customer = customerRepository.save(customer);
        log.debug("Customer created: " + customer);

        return customer;
    }

    public Customer replaceCustomer(Long id, Customer updatedCustomer) {

        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND_FOR_THIS_ID + id));

        updatedCustomer = customerRepository.save(updatedCustomer);
        log.debug(updatedCustomer.toJson());

        return updatedCustomer;
    }

    //public String toJson(Customer customer) {
        //try {
            //log.info("Mapping customer to json");
            //return mapper.writeValueAsString(customer);
        //}
        //catch(Exception e) {
            //log.error(e.getClass().getName(), e.getMessage());
            //return "";
        //}
    //}

    public Customer fromJson(String json) {
        try {
            log.info("Mapping customer from json");
            return mapper.readValue(json, Customer.class);
        }
        catch(Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return new Customer();
        }
    }

}
