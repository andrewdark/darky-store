package ua.pp.darknsoft.darkystore.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ua.pp.darknsoft.darkystore.dto.ErrorResponseDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception exception, WebRequest webRequest) {
        log.error("An exception occurred due to : {}", exception.getMessage());
        ErrorResponseDTO err = new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        log.error("An exception occurred due to : {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("timestamp",LocalDateTime.now().toString());
        errors.put("status", HttpStatus.BAD_REQUEST.toString());
        errors.put("path", webRequest.getDescription(false) );
        errors.put("error", "Validation Exception");
        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
        fieldErrorList.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(
            ConstraintViolationException exception) {
        log.error("An exception occurred due to : {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> constraintViolationSet = exception.getConstraintViolations();
        constraintViolationSet.forEach(constraintViolation ->
                errors.put(constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
