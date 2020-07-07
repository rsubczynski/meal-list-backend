package com.meal.list.backend.controller;

import com.meal.list.backend.error.ApiError;
import com.meal.list.backend.error.exception.DishNotFoundException;
import com.meal.list.backend.error.exception.FileStorageException;
import com.meal.list.backend.error.exception.IngredientExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        return buildResponseEntity(buildAppError(NOT_FOUND, ex));
    }

    @ExceptionHandler(FileStorageException.class)
    protected ResponseEntity<Object> handleFileStorageException(
            FileStorageException ex) {
        return buildResponseEntity(buildAppError(NOT_FOUND, ex));
    }

    @ExceptionHandler(DishNotFoundException.class)
    protected ResponseEntity<Object> handleDishNotFoundException(
            DishNotFoundException ex) {
        return buildResponseEntity(buildAppError(NOT_FOUND, ex));
    }

    @ExceptionHandler(IngredientExistException.class)
    protected ResponseEntity<Object> handleIngredientExistException(
            IngredientExistException ex) {
        return buildResponseEntity(buildAppError(NOT_ACCEPTABLE, ex));
    }

    private ApiError buildAppError(HttpStatus httpStatus, Exception ex){
        ApiError apiError = new ApiError(httpStatus);
        apiError.setMessage(ex.getMessage());
        return apiError;
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
