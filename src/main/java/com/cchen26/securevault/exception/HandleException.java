package com.cchen26.securevault.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.cchen26.securevault.domain.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.stream.Collectors;

import static com.cchen26.securevault.utils.RequestUtils.handleErrorResponse;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-10-19
 */

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class HandleException extends ResponseEntityExceptionHandler implements ErrorController {
    private final HttpServletRequest request;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest webRequest) {
        log.error("handleExceptionInternal: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(exception.getMessage(), getRootCauseMessage(exception), request, statusCode), statusCode);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode statusCode, WebRequest webRequest) {
        log.error("handleMethodArgumentNotValid: {}", exception.getMessage());
        var fieldErrors = exception.getBindingResult().getFieldErrors();
        var fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(handleErrorResponse(fieldsMessage, getRootCauseMessage(exception), request, statusCode), statusCode);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Response> apiException(ApiException exception) {
        log.error("ApiException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(exception.getMessage(), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response> badCredentialsException(BadCredentialsException exception) {
        log.error("BadCredentialsException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(exception.getMessage(), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Response> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        log.error("SQLIntegrityConstraintViolationException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(exception.getMessage().contains("Duplicate entry") ? "Information already exists" : exception.getMessage(), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<Response> unrecognizedPropertyException(UnrecognizedPropertyException exception) {
        log.error("UnrecognizedPropertyException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(exception.getMessage(), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> accessDeniedException(AccessDeniedException exception) {
        log.error("AccessDeniedException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse("Access denied. You don't have access", getRootCauseMessage(exception), request, FORBIDDEN), FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exception(Exception exception) {
        log.error("Exception: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(processErrorMessage(exception), getRootCauseMessage(exception), request, INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Response> transactionSystemException(TransactionSystemException exception) {
        log.error("TransactionSystemException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(processErrorMessage(exception), getRootCauseMessage(exception), request, INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Response> emptyResultDataAccessException(EmptyResultDataAccessException exception) {
        log.error("EmptyResultDataAccessException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(exception.getMessage(), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<Response> credentialsExpiredException(CredentialsExpiredException exception) {
        log.error("CredentialsExpiredException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(exception.getMessage(), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Response> disabledException(DisabledException exception) {
        log.error("DisabledException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse("User account is currently disabled", getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<Response> lockedException(LockedException exception) {
        log.error("LockedException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(exception.getMessage(), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Response> duplicateKeyException(DuplicateKeyException exception) {
        log.error("DuplicateKeyException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(processErrorMessage(exception), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response> dataIntegrityViolationException(DataIntegrityViolationException exception) {
        log.error("DataIntegrityViolationException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(processErrorMessage(exception), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Response> dataAccessException(DataAccessException exception) {
        log.error("DataAccessException: {}", exception.getMessage());
        return new ResponseEntity<>(handleErrorResponse(processErrorMessage(exception), getRootCauseMessage(exception), request, BAD_REQUEST), BAD_REQUEST);
    }

    private String processErrorMessage(Exception exception) {
        if(exception instanceof ApiException) { return exception.getMessage(); }
        //if(exception instanceof TransactionSystemException) { return getRootCauseMessage(exception).split(":")[1]; }
        if (exception.getMessage() != null) {
            if (exception.getMessage().contains("duplicate") && exception.getMessage().contains("AccountVerifications")) {
                return "You already verified your account.";
            }
            if (exception.getMessage().contains("duplicate") && exception.getMessage().contains("ResetPasswordVerifications")) {
                return "We already sent you an email to reset your password.";
            }
            if (exception.getMessage().contains("duplicate") && exception.getMessage().contains("Key (email)")) {
                return "Email already exists. Use a different email and try again.";
            }
            if (exception.getMessage().contains("duplicate")) {
                return "Duplicate entry. Please try again.";
            }
        }
        return "An error occurred. Please try again.";
    }
}