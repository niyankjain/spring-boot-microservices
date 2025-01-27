package com.easybytes.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.easybytes.accounts.constants.AccountsConstants;
import com.easybytes.accounts.dto.AccountsDto;
import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.entity.Accounts;
import com.easybytes.accounts.entity.Customer;
import com.easybytes.accounts.exception.CustomerAlreadyExistsException;
import com.easybytes.accounts.exception.ResourceNotFoundException;
import com.easybytes.accounts.mapper.AccountsMapper;
import com.easybytes.accounts.mapper.CustomerMapper;
import com.easybytes.accounts.repository.AccountsRepository;
import com.easybytes.accounts.repository.CustomerRepository;
import com.easybytes.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

  private CustomerRepository customerRepository;
  private AccountsRepository accountsRepository;
  private CustomerMapper customerMapper;
  private AccountsMapper accountsMapper;

  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = customerMapper.toEntity(customerDto);
    Optional<Customer> customerEntity = customerRepository.findByMobileNumber(customer.getMobileNumber());
    if(customerEntity.isPresent()) {
      throw new CustomerAlreadyExistsException("Customer already exists with mobile number " + customer.getMobileNumber());
    }

    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createNewAccount(savedCustomer));
  }

  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountsConstants.SAVINGS);
    newAccount.setBranchAddress(AccountsConstants.ADDRESS);
    return newAccount;
  }

  @Override
  public CustomerDto fetchAccount(String mobileNumber) {

    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

    CustomerDto customerDto = customerMapper.toDto(customer);

    Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

    AccountsDto accountsDto = accountsMapper.toDto(accounts);
    customerDto.setAccountsDto(accountsDto);
    return customerDto;

  }

  @Override
  public boolean updateAccount(CustomerDto customerDto) {
    boolean isUpdated = false;
    AccountsDto accountsDto = customerDto.getAccountsDto();
    if(accountsDto !=null ){
      Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
          () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
      );
      accounts.setAccountType("Loan Account");
      accounts = accountsRepository.save(accounts);

      Long customerId = accounts.getCustomerId();
      Customer customer = customerRepository.findById(customerId).orElseThrow(
          () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
      );

      customer.setEmail(customerDto.getEmail());
      customerRepository.save(customer);
      isUpdated = true;
    }
    return  isUpdated;
  }

  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
    );
    accountsRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.deleteById(customer.getCustomerId());
    return true;
  }
}
