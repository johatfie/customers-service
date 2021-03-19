package com.widgets_are_us.customers_service.repositories;

import com.widgets_are_us.customers_service.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address save(Address address);

    void deleteAddressById(Long id);

    Address findAddressById(Long id);
}
