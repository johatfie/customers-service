package com.widgets_are_us.customers_service.controllers;

import com.widgets_are_us.customers_service.exceptions.ResourceNotFoundException;
import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.models.CompleteCustomer;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.models.CustomerAddress;
import com.widgets_are_us.customers_service.repositories.AddressRepository;
import com.widgets_are_us.customers_service.repositories.CustomerAddressRepository;
import com.widgets_are_us.customers_service.repositories.CustomerRepository;
import com.widgets_are_us.customers_service.services.CustomerService;
import lombok.extern.log4j.Log4j2;
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

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/v1/customer/")
public class CustomerController {

    private final AddressRepository addressRepository;
    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(AddressRepository addressRepository,
                              CustomerAddressRepository customerAddressRepository,
                              CustomerRepository customerRepository,
                              CustomerService customerService) {
        this.addressRepository = addressRepository;
        this.customerAddressRepository = customerAddressRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public Customer findCustomerById(@PathVariable(value = "id") Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CustomerService.CUSTOMER_NOT_FOUND_FOR_THIS_ID + id));
    }

    @ResponseBody
    @GetMapping(value = "/findByFirstNameAndLastName")
    public List<Customer> findByFirstNameAndLastName(@RequestParam("firstName") String firstName,
                                                     @RequestParam("lastName") String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @ResponseBody
    @GetMapping(value = "/findByBusinessName/{businessName}")
    public List<Customer> findByBusinessName(@PathVariable(value = "businessName") String businessName) {
        return customerRepository.findByBusinessName(businessName);
    }

    @ResponseBody
    @GetMapping(value = "/findByEmail/{email}")
    public List<Customer> findByEmail(@PathVariable(value = "email") String email) {
        return customerRepository.findByEmail(email);
    }

    @ResponseBody
    @GetMapping(value = "/findByPhoneNumber/{phoneNumber}")
    public List<Customer> findByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
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
    public Customer replaceCustomer(@PathVariable(value = "id") Long id, @RequestBody Customer customer) {
        return customerService.replaceCustomer(id, customer);
    }

    @ResponseBody
    @GetMapping(value = "/{id}/complete")
    public CompleteCustomer getCompleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(CustomerService.CUSTOMER_NOT_FOUND_FOR_THIS_ID + customerId));

        CustomerAddress defaultCustomerAddress =
                customerAddressRepository.findByCustomerIdWhereDefaultAddressIsTrue(customerId).orElse(null);
        Address defaultAddress = null;

        if(defaultCustomerAddress != null) {
            defaultAddress = addressRepository.findAddressById(defaultCustomerAddress.getId()).orElse(null);
        }

        List<CustomerAddress> customerAddresses = customerAddressRepository.findByCustomerId(customerId);
        //List<Long> addressIds = new ArrayList<>();
        //List<Long> addressIds = customerAddresses.stream().mapToLong(ca -> ca.getAddressId());
        //customerAddresses.stream().mapToLong(ca -> ca.getAddressId()).forEach(id ->
               //foundAddresses.add(addressRepository.findAllById(id))
                //);
        List<Address> foundAddresses = null;

        if(!customerAddresses.isEmpty()) {
            foundAddresses = new ArrayList<>(
                    addressRepository.findAllById(customerAddresses.stream().mapToLong(CustomerAddress::getAddressId)));
        }

        //for(CustomerAddress ca : customerAddresses) {
            //Address foundAddress = addressRepository.findAddressById(ca.getId());
            //addressIds.add();
        //}

        CompleteCustomer completeCustomer = CompleteCustomer.builder()
                .customer(customer).defaultAddress(defaultAddress).addressList(foundAddresses).build();
        log.info("Complete customer assembled: " + completeCustomer.toJson());

        return completeCustomer;
    }
}
