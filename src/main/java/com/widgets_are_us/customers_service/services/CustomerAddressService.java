package com.widgets_are_us.customers_service.services;

import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.models.CustomerAddress;
import com.widgets_are_us.customers_service.repositories.CustomerAddressRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;

    @Autowired
    public CustomerAddressService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }

    public void linkCustomerToAddress(Long customerId, Long addressId, Boolean defaultAddress) {
        customerAddressRepository.save(customerId, addressId, defaultAddress);
    }

    public void linkCustomerToAddress(Customer customer, Address address, Boolean defaultAddress) {

        if(true == defaultAddress) {
            removeDefaultFromCustomerAddress(customer.getId());
        }

        customerAddressRepository.save(customer.getId(), address.getId(), defaultAddress);
    }

    public void removeDefaultFromCustomerAddress(Long customerId) {
        CustomerAddress defaultCustomerAddress =
                customerAddressRepository.findByCustomerIdWhereDefaultAddressIsTrue(customerId);

        if(defaultCustomerAddress != null) {
            customerAddressRepository.save(defaultCustomerAddress.getCustomerId(),
                    defaultCustomerAddress.getAddressId(), false);
        }
    }

}
