package com.example.numbers.exception.handlers;

import com.example.numbers.dto.ResponseDto;
import com.example.numbers.exception.NoElementInListException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class NumbersApplicationExceptionHandler {

    @ExceptionHandler(NoElementInListException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseDto handleHtsApiFailureException(HttpServletRequest req, NoElementInListException ex) {
        return new ResponseDto(HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(), null);
    }
}