package com.widgets_are_us.customers_service.repositories;

import com.widgets_are_us.customers_service.models.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
    CustomerAddress save(Long customerId, Long id, Boolean defaultAddress);

    @Query("SELECT ca FROM CustomerAddress ca WHERE ca.customerId = :customerId AND ca.defaultAddress = true")
    CustomerAddress findByCustomerIdWhereDefaultAddressIsTrue(Long customerId);

    List<CustomerAddress> findByCustomerId(Long customerId);
}
