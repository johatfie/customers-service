package com.widgets_are_us.customers_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.repositories.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressService {

    private static final ObjectMapper mapper = new ObjectMapper();

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(String json) {

        log.info("Mapping customer from json: " + json);
        Address address = fromJson(json);
        address = addressRepository.save(address);
        log.debug("Address created: " + address);

        return address;
    }


    public static Customer fromJson(String json) {
        try {
            log.info("Mapping Address from json");
            return mapper.readValue(json, Address.class);
        }
        catch(Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return new Customer();
        }
    }


}
