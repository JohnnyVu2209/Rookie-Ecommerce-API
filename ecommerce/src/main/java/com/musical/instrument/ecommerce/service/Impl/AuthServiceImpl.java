package com.musical.instrument.ecommerce.service.Impl;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.Entity.Cart;
import com.musical.instrument.ecommerce.Entity.ERole;
import com.musical.instrument.ecommerce.Entity.Role;
import com.musical.instrument.ecommerce.dto.response.ErrorCode;
import com.musical.instrument.ecommerce.dto.response.ResponseDTO;
import com.musical.instrument.ecommerce.dto.response.SuccessCode;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.exception.EmailExistsException;
import com.musical.instrument.ecommerce.exception.UserNameExistsException;
import com.musical.instrument.ecommerce.payload.request.LoginRequest;
import com.musical.instrument.ecommerce.payload.request.SignUpRequest;
import com.musical.instrument.ecommerce.payload.response.JwtResponse;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
import com.musical.instrument.ecommerce.repositpory.RoleRepository;
import com.musical.instrument.ecommerce.security.jwt.JwtUltil;
import com.musical.instrument.ecommerce.security.services.UserDetailsImpl;
import com.musical.instrument.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUltil jwtUltil;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public JwtResponse SignIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUltil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                                        .map(item -> item.getAuthority())
                                        .collect(Collectors.toList());

        return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles);
    }

    @Override
    public ResponseDTO SignUp(SignUpRequest signUpRequest) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            if(accountRepository.existsByUsername(signUpRequest.getUsername())){
                throw new UserNameExistsException(ErrorCode.ERR_USERNAME_ALREADY_TAKEN);
            }
            if(accountRepository.existsByEmail(signUpRequest.getEmail())){
                throw new EmailExistsException(ErrorCode.ERR_EMAIL_ALREADY_TAKEN);
            }

            Account account = new Account(signUpRequest.getUsername(),
                    passwordEncoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getEmail());
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByRole(ERole.ROLE_USER).
                    orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND));
            roles.add(userRole);
            account.setRoles(roles);
            account.setCreateDate(new Date());
            accountRepository.save(account);
            response.setSuccessCode(SuccessCode.SIGN_UP_SUCCESS);
        }catch (Exception e){
            throw new CreateDataFailException(ErrorCode.ERR_SIGN_UP_FAIL);
        }

        return response;
    }
}
