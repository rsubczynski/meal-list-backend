package com.meal.list.backend.controller;

import com.meal.list.backend.error.ApiError;
import com.meal.list.backend.error.exception.DishListIsEmptyException;
import com.meal.list.backend.error.exception.DishNotFoundException;
import com.meal.list.backend.error.exception.IngredientNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IngredientNotFoundException.class)
    protected ResponseEntity<ApiError> handleEntityNotFound(
            IngredientNotFoundException ex) {
        return buildResponseEntity(buildAppError(NOT_FOUND, ex));
    }


    @ExceptionHandler(DishNotFoundException.class)
    protected ResponseEntity<ApiError> handleDishNotFoundException(
            DishNotFoundException ex) {
        return buildResponseEntity(buildAppError(NOT_FOUND, ex));
    }

    @ExceptionHandler(DishListIsEmptyException.class)
    protected ResponseEntity<ApiError> handleDishListIsEmptyException(
            DishListIsEmptyException ex) {
        return buildResponseEntity(buildAppError(NOT_FOUND, ex));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ApiError> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        return buildResponseEntity(buildAppError(NOT_ACCEPTABLE, ex));
    }

    private ApiError buildAppError(HttpStatus httpStatus, Exception ex){
        return new ApiError(httpStatus, ex);
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
