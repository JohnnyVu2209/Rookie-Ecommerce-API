package com.musical.instrument.ecommerce.service;

import com.musical.instrument.ecommerce.dto.response.ResponseDTO;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.payload.request.LoginRequest;
import com.musical.instrument.ecommerce.payload.request.SignUpRequest;
import com.musical.instrument.ecommerce.payload.response.JwtResponse;

public interface AuthService {
    JwtResponse SignIn(LoginRequest loginRequest);

    ResponseDTO SignUp(SignUpRequest signUpRequest) throws CreateDataFailException;
}
