package com.example.mscustomer.exception;

import com.example.mscustomer.enums.ExceptionConstant;
import com.example.mscustomer.model.response.ExceptionResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleConstraintValidation(MethodArgumentNotValidException exception){

        return ExceptionResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ExceptionResponse handleNotFoundException(NotFoundException exception){
        return ExceptionResponse.builder()
                .code(ExceptionConstant.CUSTOMER_NOT_FOUND.getCode())
                .message(ExceptionConstant.CUSTOMER_NOT_FOUND.getMessage())
                .build();
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ExceptionResponse handleTypeMismatchException(MethodArgumentTypeMismatchException exception){
        return ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ExceptionConstant.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION.getMessage())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ExceptionResponse handleConstraintViolationException(ConstraintViolationException exception){
        return ExceptionResponse.builder()
                .code(exception.getErrorCode())
                .message(exception.getCause().getMessage())
                .build();
    }
}
