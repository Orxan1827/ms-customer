package com.example.mscustomer.controller;

import com.example.mscustomer.model.request.CustomerRequest;
import com.example.mscustomer.model.response.CustomerResponse;
import com.example.mscustomer.repository.CustomerRepository;
import com.example.mscustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@Valid @RequestBody CustomerRequest request) {
        customerService.saveCustomer(request);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/pinCode/{pinCode}")
    public CustomerResponse getCustomerByPinCode(@PathVariable String pinCode) {
        return customerService.getCustomerByPinCode(pinCode);
    }

    @GetMapping("{id}")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request){
        customerService.update(id,request);
    }


}
