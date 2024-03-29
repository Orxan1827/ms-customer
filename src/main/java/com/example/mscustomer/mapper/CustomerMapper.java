package com.example.mscustomer.mapper;

import com.example.mscustomer.entity.Customer;
import com.example.mscustomer.enums.CustomerStatus;
import com.example.mscustomer.model.request.CustomerRequest;
import com.example.mscustomer.model.request.CustomerUpdateRequest;
import com.example.mscustomer.model.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer mapRequestToCustomer(CustomerRequest request) {
        return Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .pinCode(request.getPinCode())
                .status(request.getStatus())
                .build();
    }

    public CustomerResponse mapCustomerToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .pinCode(customer.getPinCode())
                .createdAt(customer.getCreatedAt())
                .build();
    }

    public Customer mapRequestToCustomer(CustomerUpdateRequest request, Customer customer) {
        if (request.getName() != null)
            customer.setName(request.getName());
        if (request.getSurname() != null)
            customer.setSurname(request.getSurname());
        if (request.getStatus() != null)
            customer.setStatus(request.getStatus());
        return customer;
    }


}