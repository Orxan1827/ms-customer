package com.example.mscustomer.service.impl;

import com.example.mscustomer.entity.Customer;
import com.example.mscustomer.exception.NotFoundException;
import com.example.mscustomer.mapper.CustomerMapper;
import com.example.mscustomer.mapper.CustomerMapperStruct;
import com.example.mscustomer.model.request.CustomerRequest;
import com.example.mscustomer.model.request.CustomerUpdateRequest;
import com.example.mscustomer.model.response.CustomerResponse;
import com.example.mscustomer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static com.example.mscustomer.enums.CustomerStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CustomerServiceImplTest {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerMapperStruct customerMapperStruct;
    private CustomerServiceImpl customerService;
    private CustomerRequest customerRequest;
    private CustomerUpdateRequest customerUpdateRequest;
    private CustomerResponse customerResponse;
    private Customer customer;
    private List<Customer> customers;
    private List<CustomerResponse> customerResponseList;
    private Customer updatedCustomer;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerMapper = Mockito.mock(CustomerMapper.class);
        customerMapperStruct = Mockito.mock(CustomerMapperStruct.class);

        customerService = new CustomerServiceImpl(customerRepository, customerMapper, customerMapperStruct);

        customerRequest = CustomerRequest.builder().name("orxan").surname("rustem").status(ACTIVE).pinCode("123456").build();

        customerUpdateRequest = CustomerUpdateRequest.builder().name("orxan").surname("rustem").status(ACTIVE).build();

        customerResponse = CustomerResponse.builder().id(1L).name("orxan").surname("rustem").pinCode("123456").createdAt(LocalDateTime.now()).status(ACTIVE).build();

        customer = Customer.builder().id(1L).name("orxan").surname("rustem").status(ACTIVE).pinCode("123456").createdAt(LocalDateTime.now()).build();

        customers = List.of(customer);

        customerResponseList = List.of(customerResponse);

        updatedCustomer = Customer.builder().id(1L).name("orxan").surname("rustemli").status(ACTIVE).pinCode("123457").createdAt(LocalDateTime.now()).build();

    }

    @Test
    void testWhenSaveCustomerCalledValidRequest() {
        Mockito.when(customerMapper.mapRequestToCustomer(customerRequest)).thenReturn(customer);
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customerService.saveCustomer(customerRequest);

        Mockito.verify(customerMapper, Mockito.times(1)).mapRequestToCustomer(customerRequest);
        Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
    }

    @Test
    void testWhenGetAllCustomerCalled_itShouldReturnCustomerResponseList() {
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        Mockito.when(customerMapperStruct.customerToResponse(customer)).thenReturn(customerResponse);

        List<CustomerResponse> result = customerService.getAllCustomer();

        assertNotNull(result);
        assertEquals(customerResponseList, result);

        Mockito.verify(customerRepository, Mockito.times(1)).findAll();
        Mockito.verify(customerMapperStruct, Mockito.times(1)).customerToResponse(customer);
    }

    @Test
    void testWhenGetCustomerByPinCodeCalledByValidPinCode_itShouldReturnCustomerResponse() {
        Mockito.when(customerRepository.findCustomerByPinCode("123456")).thenReturn(Optional.of(customer));
        Mockito.when(customerMapperStruct.customerToResponse(customer)).thenReturn(customerResponse);

        CustomerResponse result = customerService.getCustomerByPinCode("123456");

        assertNotNull(result);
        assertEquals(customerResponse, result);

        Mockito.verify(customerRepository, Mockito.times(1)).findCustomerByPinCode("123456");
        Mockito.verify(customerMapperStruct, Mockito.times(1)).customerToResponse(customer);
    }

    @Test
    void testWhenGetCustomerByPinCodeCalledByNotExistPinCode_itShouldReturnCustomerNotFound() {
        Mockito.when(customerRepository.findCustomerByPinCode("12345678")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> customerService.getCustomerByPinCode("12345678"));

        Mockito.verify(customerRepository, Mockito.times(1)).findCustomerByPinCode("12345678");

    }

    @Test
    void testWhenGetCustomerByIdCalledById_itShouldReturnCustomerResponse() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Mockito.when(customerMapperStruct.customerToResponse(customer)).thenReturn(customerResponse);

        CustomerResponse result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals(customerResponse, result);

        Mockito.verify(customerRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(customerMapperStruct, Mockito.times(1)).customerToResponse(customer);
    }

    @Test
    void testWhenGetCustomerByIdCalledByIdIfCustomerDoesNotExist_itShouldReturnNotFoundException() {
        Mockito.when(customerRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> customerService.getCustomerById(2L));

        Mockito.verify(customerRepository, Mockito.times(1)).findById(2L);
    }

    @Test
    void testWhenDeleteCustomerCalledById_itShouldDeleteCustomer() {
        Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.of(customer));
        Mockito.doNothing().when(customerRepository).delete(customer);

        customerService.deleteCustomer(1L);

        Mockito.verify(customerRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(customerRepository, Mockito.times(1)).delete(customer);
    }

    @Test
    void whenUpdateCustomerCalledByIdAndRequest_itShouldUpdateCustomer() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Mockito.when(customerMapper.mapRequestToCustomer(customerUpdateRequest,customer)).thenReturn(updatedCustomer);
        Mockito.when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);

        customerService.update(1L, customerUpdateRequest);

        assertNotEquals(customer, updatedCustomer);

        Mockito.verify(customerRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(customerMapper, Mockito.times(1)).mapRequestToCustomer(customerUpdateRequest,customer);
        Mockito.verify(customerRepository, Mockito.times(1)).save(updatedCustomer);
    }

}