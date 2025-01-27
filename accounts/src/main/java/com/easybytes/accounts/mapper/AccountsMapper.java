package com.easybytes.accounts.mapper;

import org.mapstruct.Mapper;

import com.easybytes.accounts.dto.AccountsDto;
import com.easybytes.accounts.entity.Accounts;

@Mapper(componentModel = "spring")
public interface AccountsMapper {

  public AccountsDto toDto(Accounts accounts);

  public Accounts toEntity(AccountsDto accountsDto);

}
