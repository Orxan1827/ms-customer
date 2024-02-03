package com.example.mscustomer.service.impl;

import com.example.mscustomer.entity.Customer;
import com.example.mscustomer.exception.NotFoundException;
import com.example.mscustomer.mapper.CustomerMapper;
import com.example.mscustomer.mapper.CustomerMapperStruct;
import com.example.mscustomer.model.request.CustomerRequest;
import com.example.mscustomer.model.response.CustomerResponse;
import com.example.mscustomer.repository.CustomerRepository;
import com.example.mscustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerMapperStruct customerMapperStruct;

    @Override
    public void saveCustomer(CustomerRequest request) {
        Customer customer = customerMapper.mapRequestToCustomer(request);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapperStruct::customerToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getCustomerByPinCode(String pinCode) {
        return customerRepository.findCustomerByPinCode(pinCode)
                .map(customerMapperStruct::customerToResponse)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id).map(customerMapperStruct::customerToResponse).orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
        customerRepository.delete(customer);
    }

    @Override
    public void update(Long id, CustomerRequest request) {
       Customer customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
       Customer updatedCustomer =customerMapper.mapRequestToCustomer(request);
        customerRepository.save(updatedCustomer);
    }
}
