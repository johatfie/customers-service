package com.widgets_are_us.customers_service.controllers;

import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.repositories.AddressRepository;
import com.widgets_are_us.customers_service.services.AddressService;
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

@Slf4j
@RestController
@RequestMapping("/v1/address")
public class AddressController {

    private final AddressRepository addressRepository;
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressRepository addressRepository,
                             AddressService addressService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public Address findAddressById(@PathVariable(value = "id") Long id) {
        return addressRepository.findAddressById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddressById(@PathVariable(value = "id") Long id) {
        addressRepository.deleteAddressById(id);
    }

    @ResponseBody
    @PostMapping(value = "/new",
            consumes = "application/json")
    public Address createAddress(@RequestParam("address") String address) {
        return addressService.createAddress(address);
    }

    @ResponseBody
    @PutMapping(value = "/{id}")
    public Address updateAddress(@PathVariable(value = "") Long id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }
}
