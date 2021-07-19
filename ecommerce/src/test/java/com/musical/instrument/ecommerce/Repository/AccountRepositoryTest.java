package com.musical.instrument.ecommerce.Repository;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.Entity.Cart;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void CreateAccount(){
        Account account = new Account();
        account.setUsername("ABC");
        account.setPassword("123456");
        account.setFull_name("Khôn Vũ");
        account.setSex(true);
        account.setEmail("abc@email.com");
        account.setCreateDate(new Date());
        account.setCart(null);
        assertNotNull(accountRepository.save(account));
    }

    @Test
    public void UpdateAccount(){
        Optional<Account> account = accountRepository.findByUsername("ABC");
        assertNotEquals(Optional.empty(),account.isEmpty());
        account.get().setEmail("CDE@email.com");
        account.get().setPhone("0123456789");

        assertNotNull(accountRepository.save(account.get()));

        assertEquals("CDE@email.com",account.get().getEmail());
        assertEquals("0123456789",account.get().getPhone());
    }
    @Test
    public void DeleteAccount(){
        Optional<Account> account = accountRepository.findByUsername("ABC");
        assertNotEquals(Optional.empty(),account.isEmpty());
        accountRepository.deleteById(account.get().getId());

        Optional<Account> deleted = accountRepository.findById(account.get().getId());

        assertEquals(Optional.empty(),deleted);
    }
}
