package com.musical.instrument.ecommerce.controller;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.convert.UserConvert;
import com.musical.instrument.ecommerce.dto.request.User.UserDTO;
import com.musical.instrument.ecommerce.dto.response.ErrorCode;
import com.musical.instrument.ecommerce.dto.response.ResponseDTO;
import com.musical.instrument.ecommerce.dto.response.SuccessCode;
import com.musical.instrument.ecommerce.exception.LoadDataFailException;
import com.musical.instrument.ecommerce.exception.UpdateDataFailException;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
import com.musical.instrument.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserConvert userConvert;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAll() throws LoadDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<Account> account = (List<Account>) accountRepository.findAll();
            List<UserDTO> user = account.stream().map(x -> userConvert.userDTO(x)).collect(Collectors.toList());
            responseDTO.setData(user);
            responseDTO.setSuccessCode(SuccessCode.LOAD_USER_SUCCESS);
        }catch (Exception e){
            throw new LoadDataFailException(ErrorCode.ERR_LOAD_USER_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> UpdateRole(@PathVariable("id") Long accountId) throws UpdateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        UserDTO userDTO = userService.AddRole(accountId);
        responseDTO.setData(userDTO);
        responseDTO.setSuccessCode(SuccessCode.UPDATE_USER_ROLE_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> DeleteRole(@PathVariable("id") Long accountId) throws UpdateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        UserDTO userDTO = userService.DeleteRole(accountId);
        responseDTO.setData(userDTO);
        responseDTO.setSuccessCode(SuccessCode.DELETE_USER_ROLE_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
