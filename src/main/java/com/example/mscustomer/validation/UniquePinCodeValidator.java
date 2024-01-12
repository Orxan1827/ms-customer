package com.example.mscustomer.validation;

import com.example.mscustomer.annotation.UniquePinCode;
import com.example.mscustomer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@RequiredArgsConstructor
public class UniquePinCodeValidator implements ConstraintValidator<UniquePinCode,String > {
    
    private final CustomerRepository customerRepository;

    @Override
    public boolean isValid(String pinCode, ConstraintValidatorContext context) {

        return !customerRepository.existsCustomerByPinCode(pinCode);

    }

}
