package com.example.mscustomer.service;

import com.example.mscustomer.model.request.CustomerRequest;
import com.example.mscustomer.model.request.CustomerUpdateRequest;
import com.example.mscustomer.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService { 
    void saveCustomer(CustomerRequest request);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse getCustomerByPinCode(String pinCode);
    CustomerResponse getCustomerById(Long id);
    void deleteCustomer(Long id);
    void update(Long id, CustomerUpdateRequest updateRequest);


}
