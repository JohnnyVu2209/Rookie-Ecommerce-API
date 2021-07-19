package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.musical.instrument.ecommerce.Entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    Optional<Account> findByUsername(String name);

    Optional<Account> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
