package com.musical.instrument.ecommerce.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @Pattern(regexp = "^[\\\\w!#$%&'*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$"
    ,message = "Email not valid")
    String email;

    @NotBlank(message = "Username cannot be blank")
    String username;

    @Size(min = 6, message = "Password cannot be less than 6 characters")
    @NotBlank(message = "Password cannot be blank")
    String password;

}
