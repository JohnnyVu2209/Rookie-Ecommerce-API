package com.musical.instrument.ecommerce.convert;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.Entity.ERole;
import com.musical.instrument.ecommerce.Entity.Role;
import com.musical.instrument.ecommerce.dto.request.User.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserConvert {
    @Autowired
    private ModelMapper mapper;

    public UserDTO userDTO(Account account){
        UserDTO userDTO = mapper.map(account, UserDTO.class);
        Set<String> roles = account.getRoles().stream()
                                   .map(role -> role.getRole().name())
                                   .collect(Collectors.toSet());
        userDTO.setRoles(roles);
        return userDTO;
    }
}
