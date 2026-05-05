package com.example.vehiculo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// DTO estándar para devolver errores en formato JSON en el microservicio de Vehículos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoErrorResponse {

    private LocalDateTime timestamp; // Momento en que ocurrió el error
    private int status;              // Código HTTP (ej. 404, 500)
    private String error;            // Tipo de error (ej. Not Found, Bad Request)
    private String mensaje;          // Mensaje descriptivo para el cliente
    private String ruta;             // Endpoint donde ocurrió el error
}
