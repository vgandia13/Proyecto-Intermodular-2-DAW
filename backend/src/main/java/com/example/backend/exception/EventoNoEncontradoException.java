package com.example.backend.exception;

public class EventoNoEncontradoException extends RuntimeException {
    public EventoNoEncontradoException(String message) {
        super(message);
    }
    
}
