package kz.maks.barter.controllers;

import kz.maks.barter.dtos.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.Bidi;
import java.util.Locale;

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
    protected BaseResponse handleValidationError(Exception e) {
        BindingResult bindingResult = null;
        if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setHasErrors(true);
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            String localizedMessage = messages.getMessage(objectError.getDefaultMessage(), objectError.getDefaultMessage());
            localizedMessage = messages.getMessage(objectError.getCode(), localizedMessage);
            baseResponse.getErrors().add(localizedMessage);
        }
        return baseResponse;
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BaseResponse handleConstraintViolationException(ConstraintViolationException e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setHasErrors(true);
        for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
            String localizedMessage = messages.getMessage(constraintViolation.getMessage());
            baseResponse.getErrors().add(localizedMessage);
        }
        return baseResponse;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BaseResponse handleError(Exception e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setHasErrors(true);
        Throwable rootCause = NestedExceptionUtils.getRootCause(e);
        rootCause = rootCause != null ? rootCause : e;
        baseResponse.getErrors().add(rootCause.getLocalizedMessage());
        return baseResponse;
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BaseResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setHasErrors(true);
        baseResponse.getErrors().add(e.getMessage());
        return baseResponse;
    }
}

