package com.example.vehiculo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VehiculoRequestDTO {

    @NotBlank(message = "La patente es obligatoria")
    private String patente;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @Min(value = 1900, message = "El año debe ser mayor a 1900")
    private int anio;

    @NotBlank(message = "El RUT del cliente es obligatorio")
    private String rutCliente;

    private boolean enReparacion;
}
