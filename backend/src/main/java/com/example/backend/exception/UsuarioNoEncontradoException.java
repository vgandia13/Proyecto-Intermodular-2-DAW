package com.example.backend.exception;

public class UsuarioNoEncontradoException  extends RuntimeException {
    public UsuarioNoEncontradoException(String message) {
        super(message);
    }

}
