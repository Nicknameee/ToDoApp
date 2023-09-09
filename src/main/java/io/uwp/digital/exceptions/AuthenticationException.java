package io.uwp.digital.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends GlobalException {
    public AuthenticationException(String exception) {
        super(HttpStatus.UNAUTHORIZED, exception);
    }
}
