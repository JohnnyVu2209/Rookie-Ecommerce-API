package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musical.instrument.ecommerce.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
