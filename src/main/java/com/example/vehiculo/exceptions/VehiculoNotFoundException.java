package com.example.vehiculo.exceptions;

// Excepción personalizada para cuando no se encuentra un vehículo
public class VehiculoNotFoundException extends RuntimeException {

    public VehiculoNotFoundException(String mensaje) {
        super(mensaje);
    }
}