package io.uwp.digital.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.uwp.digital.exceptions.serializer.ExceptionSerializer;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = ExceptionSerializer.class)
@FieldNameConstants
public class GlobalException extends RuntimeException {
    private String exception;
    private HttpStatus httpStatus;

    public GlobalException(HttpStatus httpStatus, String exception) {
        super(exception);
        this.httpStatus = httpStatus;
        this.exception = exception;
    }
}

