package com.musical.instrument.ecommerce.security.services;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email)
                                           .orElseThrow(() -> new UsernameNotFoundException("USER_NOT_FOUND"));

        return UserDetailsImpl.build(account);
    }
}
