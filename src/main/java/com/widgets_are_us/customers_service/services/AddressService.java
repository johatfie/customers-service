package com.widgets_are_us.customers_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgets_are_us.customers_service.dto.AddressDto;
import com.widgets_are_us.customers_service.exceptions.AddressNotFoundException;
import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class AddressService {

  public static final String ADDRESS_NOT_FOUND_FOR_THIS_ID = "Address not found for this id: [%s]";

  private static final ObjectMapper mapper = new ObjectMapper();

  private final AddressRepository addressRepository;


  public Address createAddress(String json) {

    log.info("Mapping customer from json: [{}]", json);
    Address address = fromJson(json);
    address = addressRepository.save(address);
    log.debug("Address created: [{}]", address);

    return address;
  }

  public Address replaceAddress(Long id, Address updatedAddress) {

    // Address address =
    addressRepository
        .findAddressById(id)
        .orElseThrow(
            () -> new AddressNotFoundException(ADDRESS_NOT_FOUND_FOR_THIS_ID.formatted(id)));

    updatedAddress = addressRepository.save(updatedAddress);
    log.debug(updatedAddress.toJson());

    return updatedAddress;
  }

  public static Address fromJson(String json) {
    try {
      log.info("Mapping Address from json");
      return new Address(mapper.readValue(json, AddressDto.class));
    } catch (Exception e) {
      log.error(e.getClass().getName(), e.getMessage());
      return new Address();
    }
  }
}
