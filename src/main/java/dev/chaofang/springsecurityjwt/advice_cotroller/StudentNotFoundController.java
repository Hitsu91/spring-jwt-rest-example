package dev.chaofang.springsecurityjwt.advice_cotroller;

import dev.chaofang.springsecurityjwt.exception.ResourceNotFoundException;
import dev.chaofang.springsecurityjwt.exception.UserExistingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class StudentNotFoundController {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException() {
        return new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Resource Not found", "Not Found");
    }

    @ExceptionHandler(value = {UserExistingException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage userExistingException() {
        return new ErrorMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "User Existing", "Existing user with same username");
    }
}
