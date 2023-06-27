package com.example.springbootex.advice;

import com.example.springbootex.controller.PersonController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice(assignableTypes = PersonController.class)
public class PersonControllerAdvice extends GlobalControllerAdvice {



}
