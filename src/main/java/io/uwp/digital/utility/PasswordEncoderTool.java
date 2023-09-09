package io.uwp.digital.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class PasswordEncoderTool {
    private final PasswordEncoder passwordEncoder;
    private static PasswordEncoder passwordEncoderTool;

    @PostConstruct
    public void initialize() {
        passwordEncoderTool = this.passwordEncoder;
    }

    public static String encodePassword(String password) {
        return passwordEncoderTool.encode(password);
    }
}
