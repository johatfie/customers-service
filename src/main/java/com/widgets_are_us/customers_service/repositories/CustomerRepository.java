package com.widgets_are_us.customers_service.repositories;

import com.widgets_are_us.customers_service.models.Customer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long id);

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    List<Customer> findByBusinessName(String businessName);

    List<Customer> findByEmail(String email);

    List<Customer> findByPhoneNumber(String phoneNumber);

    void deleteCustomerById(Long id);

    Customer save(Customer customer);
}
