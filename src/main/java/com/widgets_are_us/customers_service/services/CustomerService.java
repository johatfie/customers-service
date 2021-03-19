package com.widgets_are_us.customers_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

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
