package util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.cosmocatsmarketplacelabs.service.exception.ParamsViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public class ValidationUtilsTest {
    @Test
    void shouldGetValidationProblemDetail() {
        FieldError fieldError1 = new FieldError("objectName", "field1", "Field1 error message");
        FieldError fieldError2 = new FieldError("objectName", "field2", "Field2 error message");
        List<FieldError> errors = List.of(fieldError1, fieldError2);
        List<ParamsViolationException> validationResponse =
                errors.stream().map(err -> ParamsViolationException.builder().reason(err.getDefaultMessage()).fieldName(err.getField()).build()).toList();

        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getDescription(false)).thenReturn("uri=/test");

        ProblemDetail errorResponse = ValidationUtils.getValidationProblemDetail(validationResponse);

        assertEquals(400, errorResponse.getStatus());
        assertEquals("Field Validation Exception", errorResponse.getTitle());
        assertEquals("Request validation failed", errorResponse.getDetail());
    }
}

