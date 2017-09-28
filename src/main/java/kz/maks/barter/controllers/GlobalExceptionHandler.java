package kz.maks.barter.controllers;

import kz.maks.barter.dtos.BadResponse;
import kz.maks.barter.dtos.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-27
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSourceAccessor messages;

    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BadResponse handleValidationError(Exception e) {
        BindingResult bindingResult = null;
        if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        }
        BadResponse badResponse = new BadResponse();
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            String localizedMessage = messages.getMessage(objectError.getDefaultMessage(), objectError.getDefaultMessage());
            localizedMessage = messages.getMessage(objectError.getCode(), localizedMessage);
            badResponse.getErrors().add(localizedMessage);
        }
        return badResponse;
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BadResponse handleConstraintViolationException(ConstraintViolationException e) {
        BadResponse badResponse = new BadResponse();
        for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
            String localizedMessage = messages.getMessage(constraintViolation.getMessage());
            badResponse.getErrors().add(localizedMessage);
        }
        return badResponse;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BadResponse handleError(Exception e) {
        BadResponse badResponse = new BadResponse();
        Throwable rootCause = NestedExceptionUtils.getRootCause(e);
        rootCause = rootCause != null ? rootCause : e;
        badResponse.getErrors().add(rootCause.getLocalizedMessage());
        return badResponse;
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BadResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        BadResponse badResponse = new BadResponse();
        badResponse.getErrors().add(e.getMessage());
        return badResponse;
    }
}

