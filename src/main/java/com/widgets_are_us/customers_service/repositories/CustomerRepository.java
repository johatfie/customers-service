package com.widgets_are_us.customers_service.repositories;

import com.widgets_are_us.customers_service.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerById(Long id);

    void deleteCustomerById(Long id);

    Customer save(Customer customer);
}
