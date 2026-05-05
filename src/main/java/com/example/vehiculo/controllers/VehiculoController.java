package com.example.vehiculo.controllers;

import com.example.vehiculo.dto.request.VehiculoRequestDTO;
import com.example.vehiculo.dto.response.VehiculoResponseDTO;
import com.example.vehiculo.model.Vehiculo;
import com.example.vehiculo.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que retorna JSON automáticamente
@RequestMapping("/api/v1/vehiculos") // Ruta base del controlador
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    // Endpoint GET para obtener todos
    @GetMapping
    public ResponseEntity<List<VehiculoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(vehiculoService.obtenerTodos());
    }

    // Endpoint GET para obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> obtenerPorId(@PathVariable long id) {
        VehiculoResponseDTO vehiculo = vehiculoService.obtenerPorId(id);
        return ResponseEntity.ok(vehiculo);
    }

    // Endpoint POST para guardar nuevo
    @PostMapping
    public ResponseEntity<VehiculoResponseDTO> guardar(
            @Valid @RequestBody VehiculoRequestDTO request
    ) {
        VehiculoResponseDTO nuevo = vehiculoService.guardar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Endpoint PUT para actualizar
    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> actualizar(
            @PathVariable long id,
            @Valid @RequestBody VehiculoRequestDTO request
    ) {
        VehiculoResponseDTO actualizado = vehiculoService.actualizar(id, request);
        return ResponseEntity.ok(actualizado);
    }

    // Endpoint DELETE para eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable long id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.ok("Vehículo eliminado");
    }

    // Endpoint GET por marca
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<VehiculoResponseDTO>> buscarPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(vehiculoService.buscarPorMarca(marca));
    }

    // Endpoint GET en reparación
    @GetMapping("/reparacion")
    public ResponseEntity<List<VehiculoResponseDTO>> buscarEnReparacion() {
        return ResponseEntity.ok(vehiculoService.buscarEnReparacion());
    }
}
