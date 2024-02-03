package com.example.mscustomer.model.response;

import com.example.mscustomer.enums.CustomerStatus;
import liquibase.pro.packaged.B;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String surname;
    private String pinCode;
    private CustomerStatus status;
    private LocalDateTime createdAt;

}
