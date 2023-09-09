package io.uwp.digital.exceptions.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.uwp.digital.exceptions.GlobalException;

import java.io.IOException;

public class ExceptionSerializer extends JsonSerializer<GlobalException> {
    @Override
    public void serialize(GlobalException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(GlobalException.Fields.exception);
        jsonGenerator.writeString(e.getException());
        jsonGenerator.writeFieldName(GlobalException.Fields.httpStatus);
        jsonGenerator.writeString(e.getHttpStatus().name());
        jsonGenerator.writeEndObject();
    }
}
