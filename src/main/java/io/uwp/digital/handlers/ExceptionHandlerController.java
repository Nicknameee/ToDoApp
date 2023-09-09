package io.uwp.digital.handlers;

import io.uwp.digital.exceptions.GlobalException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleApplicationExceptions(Exception e) {
        if (e instanceof GlobalException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception);
        } else if (e instanceof MethodArgumentNotValidException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GlobalException(
                    HttpStatus.BAD_REQUEST,
                    exception
                            .getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .filter(Objects::nonNull)
                            .toList()
                            .toString()));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }
}
