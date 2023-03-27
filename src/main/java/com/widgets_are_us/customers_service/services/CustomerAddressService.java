package com.widgets_are_us.customers_service.services;

import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.models.CustomerAddress;
import com.widgets_are_us.customers_service.repositories.CustomerAddressRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;

    @Autowired
    public CustomerAddressService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }

    public void linkCustomerToAddress(Long customerId, Long addressId, Boolean defaultAddress) {
        List<CustomerAddress> customerAddresses = customerAddressRepository.findByCustomerIdAndAddressId(customerId,
                addressId);

        if(customerAddresses.isEmpty()) {
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
                customerAddressRepository.findByCustomerIdWhereDefaultAddressIsTrue(customerId).orElse(null);

        if(defaultCustomerAddressToBeMadeNonDefault != null) {
            defaultCustomerAddressToBeMadeNonDefault.setDefaultAddress(false);
            customerAddressRepository.save(defaultCustomerAddressToBeMadeNonDefault);
        }
    }

    public void removeAddressFromCustomer(Customer customer, Address address) {
        customerAddressRepository.deleteCustomerAddressByCustomerIdAndAddressId(customer.getId(), address.getId());
    }

}
