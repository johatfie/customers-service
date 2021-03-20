package com.widgets_are_us.customers_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgets_are_us.customers_service.exceptions.ResourceNotFoundException;
import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.repositories.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressService {

    public static final String ADDRESS_NOT_FOUND_FOR_THIS_ID = "Address not found for this id :: ";

    private static final ObjectMapper mapper = new ObjectMapper();

    private final AddressRepository addressRepository;

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

    public Address replaceAddress(Long id, Address updatedAddress) {

        //Address address =
        addressRepository.findAddressById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ADDRESS_NOT_FOUND_FOR_THIS_ID + id));

        updatedAddress = addressRepository.save(updatedAddress);
        log.debug(updatedAddress.toJson());

        return updatedAddress;
    }

    public static Address fromJson(String json) {
        try {
            log.info("Mapping Address from json");
            return mapper.readValue(json, Address.class);
        }
        catch(Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return new Address();
        }
    }


}
