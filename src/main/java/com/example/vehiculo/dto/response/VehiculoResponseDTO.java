package com.example.vehiculo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponseDTO {
    private Long id;
    private String patente;
    private String marca;
    private String modelo;
    private int anio;
    private boolean enReparacion;
}
