package com.easybytes.accounts.repository;

import java.util.Optional;

import com.easybytes.accounts.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  public Optional<Customer> findByMobileNumber(String mobileNumber);


}
