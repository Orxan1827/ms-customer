package com.example.mscustomer.mapper;

import com.example.mscustomer.entity.Customer;
import com.example.mscustomer.model.request.CustomerRequest;
import com.example.mscustomer.model.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapperStruct {

    @Mapping(target = "id", ignore = true)

    Customer responseToCustomer(CustomerResponse response);
    CustomerResponse customerToResponse(Customer customer);


}
