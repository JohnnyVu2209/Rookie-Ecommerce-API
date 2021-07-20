package com.musical.instrument.ecommerce.service.Impl;

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
import com.musical.instrument.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserConvert userConvert;

    @Override
    public UserDTO UpdateUser(Long accountId, Account account) throws UpdateDataFailException {
        Account acc = accountRepository.findById(accountId)
                                       .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND));
        UserDTO userDTO = new UserDTO();
        try {
            acc.setEmail(account.getEmail());
            acc.setUsername(account.getUsername());
            acc.setFull_name(account.getFull_name());
            acc.setSex(account.getSex());
            acc.setPhone(account.getPhone());
            acc = accountRepository.save(acc);
            userDTO = userConvert.userDTO(acc);
        }catch (Exception e){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_USER_FAIL);
        }
        return userDTO;
    }

    @Override
    public UserDTO AddRole(Long accountId) throws UpdateDataFailException {
        UserDTO userDTO = new UserDTO();
        Account acc = accountRepository.findById(accountId)
                                       .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND));
        try {
            Set<Role> roles = acc.getRoles();
            Role role = roleRepository.findByRole(ERole.ROLE_ADMIN)
                                      .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND));
            if(!roles.contains(role)){
                roles.add(role);
            }
            acc = accountRepository.save(acc);
            userDTO = userConvert.userDTO(acc);
        }catch (Exception e){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ROLE_FAIL);
        }
        return userDTO;
    }

    @Override
    public UserDTO DeleteRole(Long accountId) throws UpdateDataFailException {
        UserDTO userDTO = new UserDTO();
        Account acc = accountRepository.findById(accountId)
                                       .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND));
        try {
            Set<Role> roles = acc.getRoles();
            Role role = roleRepository.findByRole(ERole.ROLE_ADMIN)
                                      .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND));
            roles.removeIf(s -> s.getRole() == role.getRole());
            acc = accountRepository.save(acc);
            userDTO = userConvert.userDTO(acc);
        }catch (Exception e){
            throw new UpdateDataFailException(ErrorCode.ERR_DELETE_ROLE_FAIL);
        }
        return userDTO;
    }
}
