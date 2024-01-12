package com.example.mscustomer.model.request;

import com.example.mscustomer.annotation.UniquePinCode;
import com.example.mscustomer.enums.CustomerStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    @NotBlank(message = "Name can not be empty")
    private String name;

    @NotBlank(message = "Surname can not be empty")
    private String surname;

    @UniquePinCode
    @Size(min = 5,max = 7)
    private String pinCode;

    @NotNull(message = "Status can not be null")
    private CustomerStatus status;
}
