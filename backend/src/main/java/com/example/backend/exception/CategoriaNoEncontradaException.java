package com.example.backend.exception;


public class CategoriaNoEncontradaException extends RuntimeException {
    public CategoriaNoEncontradaException(String mensaje) {
        super(mensaje);
    }

}
