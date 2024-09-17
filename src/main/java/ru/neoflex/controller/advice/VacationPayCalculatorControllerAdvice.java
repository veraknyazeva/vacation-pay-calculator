package ru.neoflex.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.neoflex.exception.NegativeNumber;

@RestControllerAdvice
public class VacationPayCalculatorControllerAdvice {
    @ExceptionHandler(NegativeNumber.class)
    public ResponseEntity<NegativeNumber> calculateNegativeNumber(NegativeNumber ex) {
        NegativeNumber negativeNumber = new NegativeNumber(ex.getMessage());
        return new ResponseEntity<>(negativeNumber, HttpStatus.BAD_REQUEST);
    }
}
