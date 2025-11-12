package com.utn.productos_api.exception;

// Lanza un 404 Not Found (será capturada por el GlobalExceptionHandler)
public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String message) {
        super(message);
    }
}
