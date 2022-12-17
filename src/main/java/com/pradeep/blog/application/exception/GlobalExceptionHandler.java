package com.pradeep.blog.application.exception;

import com.pradeep.blog.application.constants.common.CommonConstants;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.NonUniqueResultException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, String> getMap() {
        return new HashMap<>();
    }

    private ApiResponse getApiResponse(String message, boolean isSuccess) {
        return new ApiResponse(message, isSuccess);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<ApiResponse>(this.getApiResponse(message, false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonUniqueResultException.class)
    public ResponseEntity<ApiResponse> handleNonUniqueResultException(NonUniqueResultException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<ApiResponse>(this.getApiResponse(message, false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        Map<String, String> map = this.getMap();
        errors.forEach((error) -> {
            String defaultMessage = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField();
            map.clear();
            map.put(fieldName, defaultMessage);
        });
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleUsersNotFoundException(NullPointerException exception) {
        String message= exception.getMessage();


        return new ResponseEntity<ApiResponse>(this.getApiResponse(
                message, true), HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> responseNotFoundException(ResourceNotFoundException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<ApiResponse>(this.getApiResponse(message, false), HttpStatus.NOT_FOUND);
    }
}
