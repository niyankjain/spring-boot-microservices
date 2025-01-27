package com.easybytes.accounts.mapper;

import org.mapstruct.Mapper;

import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  public CustomerDto toDto(Customer customer);

  public Customer toEntity(CustomerDto customerDto);
}
