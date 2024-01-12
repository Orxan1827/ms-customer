package com.example.mscustomer.repository;

import com.example.mscustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsCustomerByPinCode(String pinCode);

    Optional<Customer> findCustomerByPinCode(String pinCode);

}
