package io.uwp.digital.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GlobalException {
    public NotFoundException(String exception) {
        super(HttpStatus.NOT_FOUND, exception);
    }
}
