package com.widgets_are_us.customers_service.repositories;

import com.widgets_are_us.customers_service.models.CustomerAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

    //    CustomerAddress save(Long customerId, Long addressId, Boolean defaultAddress);
    CustomerAddress save(CustomerAddress customerAddress);

    @Query(
            "SELECT ca FROM CustomerAddress ca WHERE ca.customerId = :customerId AND"
                    + " ca.defaultAddress = true")
    Optional<CustomerAddress> findByCustomerIdWhereDefaultAddressIsTrue(Long customerId);

    List<CustomerAddress> findByCustomerId(Long customerId);

    List<CustomerAddress> findByCustomerIdAndAddressId(Long customerId, Long addressId);

    void deleteCustomerAddressByCustomerIdAndAddressId(Long customerId, Long AddressId);
}
