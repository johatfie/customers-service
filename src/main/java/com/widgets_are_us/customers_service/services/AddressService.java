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

    public Address updateAddress(Long id, Address addressDetails) {

        Address address = addressRepository.findAddressById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ADDRESS_NOT_FOUND_FOR_THIS_ID + id));

        if(addressDetails.getAddress1() != null) {
            address.setAddress1(addressDetails.getAddress1());
        }
        if(addressDetails.getAddress2() != null) {
            address.setAddress2(addressDetails.getAddress2());
        }
        if(addressDetails.getCity() != null) {
            address.setCity(addressDetails.getCity());
        }
        if(addressDetails.getState() != null) {
            address.setState(addressDetails.getState());
        }
        if(addressDetails.getZipcode() != null) {
            address.setZipcode(addressDetails.getZipcode());
        }
        if(addressDetails.getPhoneNumber() != null) {
            address.setPhoneNumber(addressDetails.getPhoneNumber());
        }

        Address updatedAddress = addressRepository.save(address);
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
