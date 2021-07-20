package com.musical.instrument.ecommerce.service;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.Entity.Role;
import com.musical.instrument.ecommerce.dto.request.User.UserDTO;
import com.musical.instrument.ecommerce.exception.UpdateDataFailException;

public interface UserService {
    public UserDTO UpdateUser(Long accountId,Account account) throws UpdateDataFailException;
    public UserDTO AddRole(Long accountId) throws UpdateDataFailException;
    public UserDTO DeleteRole(Long accountId) throws UpdateDataFailException;
}
