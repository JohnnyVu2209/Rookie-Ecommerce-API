package com.musical.instrument.ecommerce.dto.request.User;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Email cannot be blank")
    private String email;

    private String username;

    @Size(max = 50)
    private String full_name;

    private Boolean sex;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$",
            message = "Phone not valid")
    private String phone;

    private Set<String> roles;
}
