package com.widgets_are_us.customers_service.controllers;

import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.models.CompleteCustomer;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.models.CustomerAddress;
import com.widgets_are_us.customers_service.repositories.CustomerAddressRepository;
import com.widgets_are_us.customers_service.repositories.CustomerRepository;
import com.widgets_are_us.customers_service.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/customer/")
public class CustomerController {

    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerAddressRepository customerAddressRepository,
                              CustomerRepository customerRepository,
                              CustomerService customerService) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public Customer findCustomerById(@PathVariable(value = "id") Long id) {
        return customerRepository.findById(id);
    }

    @ResponseBody
    @GetMapping(value = "/findByFirstNameAndLastName/{firstName}/{lastName}")
    public List<Customer> findByFirstNameAndLastName(@PathVariable(value = "firstName") String firstName,
                                                     @PathVariable(value = "lastName") String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @ResponseBody
    @GetMapping(value = "/findByBusinessName/{businessName}")
    public List<Customer> findByBusinessName(@PathVariable(value = "businessName") String businessName) {
        return customerRepository.findByBusinessName(businessName);
    }

    @ResponseBody
    @GetMapping(value = "/findByEmail/{email}")
    public Optional<Customer> findByEmail(@PathVariable(value = "email") String email) {
        return customerRepository.findByEmail(email);
    }

    @ResponseBody
    @GetMapping(value = "/findByPhoneNumber/{phoneNumber}")
    public Optional<Customer> findByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCustomerById(@PathVariable(value = "id") Long id) {
        customerRepository.deleteCustomerById(id);
    }

    @ResponseBody
    @PostMapping(value = "/new",
            consumes = "application/json")
    public Customer createCustomer(@RequestParam("customer") String customer) {
        return customerService.createCustomer(customer);
    }

    @ResponseBody
    @PutMapping(value = "/{id}")
    public Customer updateCustomer(@PathVariable(value = "") Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @ResponseBody
    @GetMapping(value = "/{id}/complete")
    public CompleteCustomer getCompleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId);
        CustomerAddress defaultAddress = customerAddressRepository.findByCustomerIdWhereDefaultAddressIsTrue(customerId);
        List<CustomerAddress> addresses = customerAddressRepository.findByCustomerId(customerId);

        CompleteCustomer completeCustomer = CompleteCustomer.builder()
                .customer(customer).defaultAddress(defaultAddress).addressList(addresses).build();
        log.info("Complete customer assembled: " + completeCustomer.toJson());

        return completeCustomer;
    }
}
