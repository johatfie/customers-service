package com.widgets_are_us.customers_service.repositories;

import com.widgets_are_us.customers_service.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findById(Long id);

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    List<Customer> findByBusinessName(String businessName);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    void deleteCustomerById(Long id);

    Customer save(Customer customer);
}
