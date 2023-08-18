package com.satyam.splitwise.exceptions;

import com.satyam.splitwise.expense_group.exceptions.ExpenseGroupNotFoundException;
import com.satyam.splitwise.split.exception.InvalidPercentException;
import com.satyam.splitwise.split.exception.InvalidTotalAmountException;
import com.satyam.splitwise.user.exceptions.InvalidCredentialsException;
import com.satyam.splitwise.user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(Exception exception){
        if(exception instanceof ExpenseGroupNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
        if(exception instanceof UserNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
        if(exception instanceof InvalidPercentException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        if(exception instanceof InvalidTotalAmountException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        if(exception instanceof InvalidCredentialsException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
