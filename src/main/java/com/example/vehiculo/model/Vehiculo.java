package com.example.vehiculo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals automáticamente
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los atributos
@Builder // Permite usar VehiculoModel.builder() para construir objetos de manera fluida
@Entity 
@Table(name = "vehiculo")

public class Vehiculo {

  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La patente es obligatoria")
    @Column(nullable = false, unique = true)
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
