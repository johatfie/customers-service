package com.widgets_are_us.customers_service.controllers;

import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.repositories.CustomerRepository;
import com.widgets_are_us.customers_service.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/customer/")
public class CustomerController {

    CustomerRepository customerRepository;
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerRepository customerRepository,
                              CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public Customer findCustomerById(@PathVariable(value = "id") Long id) {
        return customerRepository.findCustomerById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCustomerById(@PathVariable(value = "id") Long id) {
        customerRepository.deleteCustomerById(id);
    }

    @ResponseBody
    @PostMapping(value = "/new",
            consumes = "application/json",
            produces = "application/json")
    public String createCustomer(@RequestParam("customer") String customer) {
        return customerService.createCustomer(customer).toJson();
    }
}
