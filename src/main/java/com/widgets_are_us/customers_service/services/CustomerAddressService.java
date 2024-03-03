package com.widgets_are_us.customers_service.services;

import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.models.CustomerAddress;
import com.widgets_are_us.customers_service.repositories.CustomerAddressRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class CustomerAddressService {

  private final CustomerAddressRepository customerAddressRepository;

  public void linkCustomerToAddress(Long customerId, Long addressId, Boolean defaultAddress) {
    List<CustomerAddress> customerAddresses =
        customerAddressRepository.findByCustomerIdAndAddressId(customerId, addressId);

    if (customerAddresses.isEmpty()) {
      customerAddressRepository.save(
          CustomerAddress.builder()
              .customerId(customerId)
              .addressId(addressId)
              .defaultAddress(defaultAddress)
              .build());
    }
  }

  public void linkCustomerToAddress(Customer customer, Address address, Boolean defaultAddress) {
    linkCustomerToAddress(customer.getId(), address.getId(), defaultAddress);
  }

  public void makeDefaultCustomerAddressNonDefault(Long customerId) {
    CustomerAddress defaultCustomerAddressToBeMadeNonDefault =
        customerAddressRepository
            .findByCustomerIdWhereDefaultAddressIsTrue(customerId)
            .orElse(null);

    if (defaultCustomerAddressToBeMadeNonDefault != null) {
      defaultCustomerAddressToBeMadeNonDefault.setDefaultAddress(false);
      customerAddressRepository.save(defaultCustomerAddressToBeMadeNonDefault);
    }
  }

  public void removeAddressFromCustomer(Customer customer, Address address) {
    customerAddressRepository.deleteCustomerAddressByCustomerIdAndAddressId(
        customer.getId(), address.getId());
  }
}
