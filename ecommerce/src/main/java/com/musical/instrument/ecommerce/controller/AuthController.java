package com.musical.instrument.ecommerce.controller;

import com.musical.instrument.ecommerce.dto.response.ResponseDTO;
import com.musical.instrument.ecommerce.dto.response.SuccessCode;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.payload.request.LoginRequest;
import com.musical.instrument.ecommerce.payload.request.SignUpRequest;
import com.musical.instrument.ecommerce.payload.response.JwtResponse;
import com.musical.instrument.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request){
        ResponseDTO responseDTO = new ResponseDTO();
        JwtResponse response = authService.SignIn(request);
        responseDTO.setData(response);
        responseDTO.setSuccessCode(SuccessCode.SIGN_IN_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody SignUpRequest request)
            throws CreateDataFailException {
        ResponseDTO response = authService.SignUp(request);
        return ResponseEntity.ok().body(response);
    }
}
