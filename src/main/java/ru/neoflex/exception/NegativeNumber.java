package ru.neoflex.exception;

import lombok.Getter;

@Getter
public class NegativeNumber extends RuntimeException{
    public NegativeNumber(String message) {
        super(message);
    }
}
