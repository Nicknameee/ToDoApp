package io.uwp.digital.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import io.uwp.digital.annotations.Timezone;
import io.uwp.digital.utility.PasswordEncoderTool;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDTO {
    @NotBlank(message = "Username can not be blank")
    @Length(min = 6, max = 16, message = "Username length is out of bounds")
    private String username;
    @NotBlank(message = "Password can not be blank")
    @Length(min = 5, message = "Password is too short")
    private String password;
    @Timezone
    private String timezone;

    @JsonSetter("password")
    public void setPassword(String password) {
        this.password = PasswordEncoderTool.encodePassword(password);
    }
}
