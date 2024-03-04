package com.widgets_are_us.customers_service.controllers;

import com.widgets_are_us.customers_service.dto.CompleteCustomerDto;
import com.widgets_are_us.customers_service.dto.CustomerDto;
import com.widgets_are_us.customers_service.exceptions.CustomerNotFoundException;
import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.models.CompleteCustomer;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.models.CustomerAddress;
import com.widgets_are_us.customers_service.repositories.AddressRepository;
import com.widgets_are_us.customers_service.repositories.CustomerAddressRepository;
import com.widgets_are_us.customers_service.repositories.CustomerRepository;
import com.widgets_are_us.customers_service.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

@Tag(name = "Customer", description = "Customer APIs")
@Log4j2
@RestController
@RequestMapping("/v1/customer/")
@AllArgsConstructor
public class CustomerController {

    private final AddressRepository addressRepository;
    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @Operation(
            summary = "Retrieve a customer by Id",
            description = "Get a customer by specifying its id")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            schema = @Schema(implementation = CustomerDto.class),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "404",
                description = "The customer was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @GetMapping(value = "/{id}")
    public CustomerDto findCustomerById(@PathVariable(value = "id") Long id) {
        return new CustomerDto(
                customerRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new CustomerNotFoundException(
                                                CustomerService.CUSTOMER_NOT_FOUND_FOR_THIS_ID
                                                        .formatted(id))));
    }

    @Operation(
            summary = "Retrieve a list of customers by first and last name",
            description = "Get a list of customers by specifying a first and last name")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            array =
                                    @ArraySchema(
                                            schema = @Schema(implementation = CustomerDto.class)),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "404",
                description = "The customer was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @GetMapping(value = "/findByFirstNameAndLastName")
    public List<CustomerDto> findByFirstNameAndLastName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName).stream()
                .map(
                        (Customer customer) -> {
                            return new CustomerDto(customer);
                        })
                .toList();
    }

    @Operation(
            summary = "Retrieve a list of customers by business name",
            description = "Get a list of customers by specifying a business name")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            array =
                                    @ArraySchema(
                                            schema = @Schema(implementation = CustomerDto.class)),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "404",
                description = "The customer was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @GetMapping(value = "/findByBusinessName/{businessName}")
    public List<CustomerDto> findByBusinessName(
            @PathVariable(value = "businessName") String businessName) {
        return customerRepository.findByBusinessName(businessName).stream()
                .map(
                        (Customer customer) -> {
                            return new CustomerDto(customer);
                        })
                .toList();
    }

    @Operation(
            summary = "Retrieve a list of customers by email address",
            description = "Get a list of customers by specifying an email address")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            array =
                                    @ArraySchema(
                                            schema = @Schema(implementation = CustomerDto.class)),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "404",
                description = "The customer was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @GetMapping(value = "/findByEmail/{email}")
    public List<CustomerDto> findByEmail(@PathVariable(value = "email") String email) {
        return customerRepository.findByEmail(email).stream()
                .map(
                        (Customer customer) -> {
                            return new CustomerDto(customer);
                        })
                .toList();
    }

    @Operation(
            summary = "Retrieve a list of customers by phone number",
            description = "Get a list of customers by specifying a phone number")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            array =
                                    @ArraySchema(
                                            schema = @Schema(implementation = CustomerDto.class)),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "404",
                description = "The customer was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @GetMapping(value = "/findByPhoneNumber/{phoneNumber}")
    public List<CustomerDto> findByPhoneNumber(
            @PathVariable(value = "phoneNumber") String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber).stream()
                .map(
                        (Customer customer) -> {
                            return new CustomerDto(customer);
                        })
                .toList();
    }

    @Operation(
            summary = "Delete a customer by Id",
            description = "Delete a customer by specifying its id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema())),
        @ApiResponse(
                responseCode = "404",
                description = "The customer was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @DeleteMapping(value = "/{id}")
    public void deleteCustomerById(@PathVariable(value = "id") Long id) {
        customerRepository.deleteCustomerById(id);
    }

    @Operation(
            summary = "Create a new customer",
            description = "Create a customer by specifying its id.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            schema = @Schema(implementation = CustomerDto.class),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @PostMapping(value = "/new", consumes = "application/json")
    public CustomerDto createCustomer(@RequestParam("customer") String customer) {
        return new CustomerDto(customerService.createCustomer(customer));
    }

    @Operation(summary = "Update a customer", description = "Replace a customer object")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            schema = @Schema(implementation = CustomerDto.class),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "404",
                description = "The customer was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @PutMapping(value = "/{id}")
    public CustomerDto replaceCustomer(
            @PathVariable(value = "id") Long id, @RequestBody CustomerDto customerDto) {
        return new CustomerDto(customerService.replaceCustomer(id, customerDto));
    }

    @Operation(
            summary = "Retrieve a complete customer by Id",
            description = "Get a complete customer with all addresses by specifying customer id")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            schema = @Schema(implementation = CompleteCustomerDto.class),
                            mediaType = "application/json")
                }),
        @ApiResponse(responseCode = "404", description = "The customer was not found"),
        @ApiResponse(responseCode = "500")
    })
    @ResponseBody
    @GetMapping(value = "/{id}/complete")
    public CompleteCustomerDto getCompleteCustomer(Long customerId) {
        Customer customer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        new CustomerNotFoundException(
                                                CustomerService.CUSTOMER_NOT_FOUND_FOR_THIS_ID
                                                        .formatted(customerId)));

        CustomerAddress defaultCustomerAddress =
                customerAddressRepository
                        .findByCustomerIdWhereDefaultAddressIsTrue(customerId)
                        .orElse(null);
        Address defaultAddress = null;

        if (defaultCustomerAddress != null) {
            defaultAddress =
                    addressRepository.findAddressById(defaultCustomerAddress.getId()).orElse(null);
        }

        List<CustomerAddress> customerAddresses =
                customerAddressRepository.findByCustomerId(customerId);
        // List<Long> addressIds = new ArrayList<>();
        // List<Long> addressIds = customerAddresses.stream().mapToLong(ca -> ca.getAddressId());
        // customerAddresses.stream().mapToLong(ca -> ca.getAddressId()).forEach(id ->
        // foundAddresses.add(addressRepository.findAllById(id))
        // );
        List<Address> foundAddresses = null;

        if (!customerAddresses.isEmpty()) {
            foundAddresses =
                    new ArrayList<>(
                            addressRepository.findAllById(
                                    customerAddresses.stream()
                                            .mapToLong(CustomerAddress::getAddressId)));
        }

        // for(CustomerAddress ca : customerAddresses) {
        // Address foundAddress = addressRepository.findAddressById(ca.getId());
        // addressIds.add();
        // }

        CompleteCustomer completeCustomer =
                CompleteCustomer.builder()
                        .customer(customer)
                        .defaultAddress(defaultAddress)
                        .addressList(foundAddresses)
                        .build();
        log.info("Complete customer assembled: " + completeCustomer.toJson());

        return new CompleteCustomerDto(completeCustomer);
    }
}
