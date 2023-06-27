package com.example.springbootex.advice;

import com.example.springbootex.exception.*;
import com.example.springbootex.modal.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GlobalControllerAdvice {

    @Autowired
    ObjectMapper mapper;

    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResponse> handleBindException(BindException e,
                                                            HttpServletRequest request) {
        logError("Request Params Not Valid", e, request);
        return validationErrorResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                              HttpServletRequest request) {
        logError("Arguments Not Valid", e, request);
        return validationErrorResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse> badRequestException(BadRequestException e, HttpServletRequest request) {
        logError("Bad Request", e, request);
        return errorResponse(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<BaseResponse> handleHttpMessageNotReadableException(Exception e,
                                                                              HttpServletRequest request) {
        logError("Bad Request", e, request);
        //get only message, strip off class name(s) in the message
        //ex: "Required request body is missing: public org.springframework.http.ResponseEntity<?>....."
        return errorResponse(HttpStatus.BAD_REQUEST, StringUtils.substringBefore(e.getMessage(), ":"));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        logError("Not Found", e, request);
        return errorResponse(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleOtherException(Exception e, HttpServletRequest request) {
        logError("Something went wrong !!", e, request);
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    private ResponseEntity<BaseResponse> errorResponse(HttpStatus status, String message) {
        BaseResponse response = new BaseResponse();
        response.setMessage(message);
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<BaseResponse> validationErrorResponse(HttpStatus status,
                                                                 MethodArgumentNotValidException exception) {
        InvalidArgumentResponse response = InvalidArgumentResponse.builder()
                // .message(getAllBindingErrorMessage(exception))
                .errorMessage(getBindingErrorMap(exception))
                .build();
        return ResponseEntity.status(status).body(response);
    }

    private String getAllBindingErrorMessage(MethodArgumentNotValidException exception) {
        if ((exception != null && !CollectionUtils.isEmpty(
                exception.getBindingResult().getAllErrors()))) {
            return exception
                    .getBindingResult()
                    .getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(" and "));
        } else {
            if (exception != null) return exception.getMessage();
            return "method argument not valid exception";
        }
    }

    private Map<String, String> getBindingErrorMap(MethodArgumentNotValidException exception) {
        return (exception != null && !CollectionUtils.isEmpty(
                exception.getBindingResult().getFieldErrors())) ?
                exception
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
                                (o1, o2) -> o1 + " and " + o2)) //handle duplicates
                : null;
    }

    private ResponseEntity<BaseResponse> validationErrorResponse(HttpStatus status,
                                                                 BindException exception) {
        InvalidArgumentResponse response = InvalidArgumentResponse.builder()
                //.message(getAllBindingErrorMessage(exception))
                .errorMessage(getBindingErrorMap(exception))
                .build();
        return ResponseEntity.status(status).body(response);
    }

    private String getAllBindingErrorMessage(BindException exception) {
        if ((exception != null && !CollectionUtils.isEmpty(
                exception.getBindingResult().getAllErrors()))) {
            return exception
                    .getBindingResult()
                    .getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
        } else {
            if (exception != null) return exception.getMessage();
            return "method argument not valid exception";
        }
    }

    private Map<String, String> getBindingErrorMap(BindException exception) {
        return (exception != null && !CollectionUtils.isEmpty(
                exception.getBindingResult().getFieldErrors())) ?
                exception
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
                                (o1, o2) -> o1 + " and " + o2)) //handle duplicates
                : null;
    }

    protected void logError(String message, Exception e, HttpServletRequest request) {
        log.error("{}, RequestURI = {}, RequestParams = {}, message = {}, headers = {}", message,
                request.getRequestURI(), getRequestParams(request), e.getMessage(),
                getHeadersInfo(request), e.getMessage());
    }



    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<BaseResponse> handleInternalServerException(InternalServerException e,
                                                                      HttpServletRequest request) {
        logError("Internal Server Error", e, request);
        String message = e.getMessage() != null ? e.getMessage() : "Internal Server Error";
        return errorResponse(e.getStatus(), message);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<BaseResponse> handleUnauthorizedException(UnauthorizedException e,
                                                                    HttpServletRequest request) {
        logError("Unauthorized Exception", e, request);
        String message = e.getMessage() != null ? e.getMessage() : "Unauthorized Exception";
        return errorResponse(e.getStatus(), message);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<BaseResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e, HttpServletRequest request) {
        logError("MissingServletRequestParameterException Exception", e, request);
        String message = e.getMessage() != null ? e.getMessage() : "MissingServletRequestParameter Exception";
        return errorResponse(HttpStatus.BAD_REQUEST, message);
    }

    protected void logError(String message, HttpStatus httpStatus, Exception e, HttpServletRequest request) {
        log.error("RequestURI - {}, ResponseStatus : {}, Response Message : {}, Related Exception : {}",
                request.getRequestURI(), httpStatus, message, e);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<BaseResponse> handleForbiddenException(ForbiddenException e, HttpServletRequest request) {
        logError("ForbiddenException Exception", e, request);
        BaseResponse response = new BaseResponse();
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<BaseResponse> handleUsernameNotFoundException(UsernameNotFoundException e,
                                                                        HttpServletRequest request) {
        logError(e.getMessage(), HttpStatus.NOT_FOUND, e, request);
        BaseResponse response = BaseResponse.builder().message(e.getMessage()).status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<BaseResponse> handleInvalidFormatException(InvalidFormatException e, HttpServletRequest request) {
        logError(e.getMessage(), HttpStatus.BAD_REQUEST, e, request);
        BaseResponse response = BaseResponse.builder().message("Invalid data format").status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        if (MapUtils.isNotEmpty(request.getParameterMap())) {
            Map<String, String> params = new HashMap<>();
            request.getParameterMap().forEach((k, v) -> params.put(k, String.join(",", v)));
            Map<String, String> pathParams = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            if (MapUtils.isNotEmpty(pathParams)) {
                params.putAll(pathParams);
            }
            return params;
        }
        return null;
    }
}