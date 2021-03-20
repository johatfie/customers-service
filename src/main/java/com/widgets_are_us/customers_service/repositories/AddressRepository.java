package com.widgets_are_us.customers_service.repositories;

import com.widgets_are_us.customers_service.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address save(Address address);

    void deleteAddressById(Long id);

    Optional<Address> findAddressById(Long id);

    @Override
    List<Address> findAllById(Iterable<Long> iterable);

    Address findAllById(LongStream mapToLong);
}
