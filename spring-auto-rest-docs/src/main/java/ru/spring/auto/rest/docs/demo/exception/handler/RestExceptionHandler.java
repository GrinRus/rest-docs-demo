package ru.spring.auto.rest.docs.demo.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        log.error("Получена необрабатываемая ошибка", exception);
        return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Boolean> handleNoSuchElementException(NoSuchElementException exception) {
        log.error("Получена необрабатываемая ошибка", exception);
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
    }
}
