package com.musical.instrument.ecommerce.service;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.Entity.ERole;
import com.musical.instrument.ecommerce.Entity.Role;
import com.musical.instrument.ecommerce.convert.UserConvert;
import com.musical.instrument.ecommerce.dto.request.User.UserDTO;
import com.musical.instrument.ecommerce.dto.response.ErrorCode;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.exception.UpdateDataFailException;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
import com.musical.instrument.ecommerce.repositpory.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserConvert userConvert;

    @Test
    public void UpdateUser() throws UpdateDataFailException {
        Account account = new Account("myusername","123456","abc@email.com");
        account.setId(1L);

        assertThrows(DataNotFoundException.class,() -> userService.UpdateUser(1L,any(Account.class)));

        Account updated = new Account("username","123457","xyz@email.com");
        updated.setId(1L);

        UserDTO userDTO = new UserDTO("xyz@email.com", "username", null, false, null, null);

        when(accountRepository.findById(anyLong())).thenReturn(java.util.Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(null);

        assertThrows(UpdateDataFailException.class,() -> userService.UpdateUser(1L,any(Account.class)));

        when(accountRepository.save(any(Account.class))).thenReturn(updated);
        when(userConvert.userDTO(any(Account.class))).thenReturn(userDTO);

        UserDTO dto = userService.UpdateUser(1L, account);
        assertEquals("xyz@email.com", dto.getEmail());
    }

    @Test
    public void AddRole() throws UpdateDataFailException {
        Account account = new Account("myusername","123456","abc@email.com");
        account.setId(1L);
        Set<Role> currRole = new HashSet<>();
        Role role = new Role();
        role.setRole(ERole.ROLE_USER);
        currRole.add(role);
        account.setRoles(currRole);

        assertThrows(DataNotFoundException.class,() -> userService.AddRole(1L));

        when(accountRepository.findById(anyLong())).thenReturn(java.util.Optional.of(account));

        Account update = new Account("username","123456","abc@email.com");
        account.setId(1L);
        Set<Role> roles = account.getRoles();
        Role admin = new Role();
        admin.setRole(ERole.ROLE_ADMIN);
        roles.add(admin);
        update.setRoles(roles);
        UserDTO userDTO = new UserDTO("abc@email.com", "username", null, false, null,
                update.getRoles().stream().map(r-> r.getRole().name()).collect(Collectors.toSet()));
        assertThrows(UpdateDataFailException.class,()->userService.AddRole(1L));
        when(roleRepository.findByRole(ERole.ROLE_ADMIN)).thenReturn(java.util.Optional.of(admin));
        when(accountRepository.save(any(Account.class))).thenReturn(update);
        when(userConvert.userDTO(any(Account.class))).thenReturn(userDTO);

        UserDTO dto = userService.AddRole(1L);
        Set<String> listRole = new HashSet<String>();
        listRole.add("ROLE_USER");
        listRole.add("ROLE_ADMIN");
        assertEquals(listRole, dto.getRoles());

    }

    @Test
    public void RemoveRole() throws UpdateDataFailException {
        Account account = new Account("myusername","123456","abc@email.com");
        account.setId(1L);
        Set<Role> currRole = new HashSet<>();
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setRole(ERole.ROLE_USER);
        role2.setRole(ERole.ROLE_ADMIN);
        currRole.add(role1);
        currRole.add(role2);
        account.setRoles(currRole);

        assertThrows(DataNotFoundException.class,() -> userService.DeleteRole(1L));

        when(accountRepository.findById(anyLong())).thenReturn(java.util.Optional.of(account));

        Account update = new Account("username","123456","abc@email.com");
        account.setId(1L);
        Set<Role> roles = account.getRoles();
        Role admin = new Role();
        admin.setRole(ERole.ROLE_ADMIN);
        roles.removeIf(s -> s.getRole() == ERole.ROLE_ADMIN);
        update.setRoles(roles);
        UserDTO userDTO = new UserDTO("abc@email.com", "username", null, false, null,
                update.getRoles().stream().map(r-> r.getRole().name()).collect(Collectors.toSet()));
        assertThrows(UpdateDataFailException.class,()->userService.DeleteRole(1L));

        when(roleRepository.findByRole(ERole.ROLE_ADMIN)).thenReturn(java.util.Optional.of(admin));
        when(accountRepository.save(any(Account.class))).thenReturn(update);
        when(userConvert.userDTO(any(Account.class))).thenReturn(userDTO);

        UserDTO dto = userService.DeleteRole(1L);
        Set<String> listRole = new HashSet<String>();
        listRole.add("ROLE_USER");
        assertEquals(listRole, dto.getRoles());
    }
}
