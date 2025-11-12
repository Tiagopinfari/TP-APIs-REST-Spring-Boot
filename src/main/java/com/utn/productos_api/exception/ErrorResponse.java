package com.utn.productos_api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp; // Momento del error [cite: 170]
    private HttpStatus status; // Código de estado HTTP [cite: 171]
    private String message; // Mensaje de error [cite: 176]
    private String path; // Ruta de la petición [cite: 177]

    // Constructor simplificado
    public ErrorResponse(HttpStatus status, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
