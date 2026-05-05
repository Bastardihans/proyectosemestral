package com.example.vehiculo.repository;

import com.example.vehiculo.model.Vehiculo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class VehiculoRepository {

    private final List<Vehiculo> vehiculos = new ArrayList<>();
    private long nextId = 1;

    // Constructor con datos iniciales de prueba
    public VehiculoRepository() {
        vehiculos.add(Vehiculo.builder()
                .id(nextId++)
                .patente("ABC123")
                .marca("Toyota")
                .modelo("Corolla")
                .anio(2020)
                .rutCliente("11.111.111-1")
                .enReparacion(false)
                .build());

        vehiculos.add(Vehiculo.builder()
                .id(nextId++)
                .patente("XYZ789")
                .marca("Hyundai")
                .modelo("Accent")
                .anio(2018)
                .rutCliente("22.222.222-2")
                .enReparacion(true)
                .build());
    }

    // Listar todos ordenados por ID
    public List<Vehiculo> findAll() {
        return vehiculos.stream()
                .sorted(Comparator.comparing(Vehiculo::getId))
                .toList();
    }

    // Buscar por ID
    public Optional<Vehiculo> findById(long id) {
        return vehiculos.stream()
                .filter(v -> v.getId() == id)
                .findFirst();
    }

    // Guardar nuevo vehículo
    public Vehiculo save(Vehiculo nuevo) {
        nuevo.setId(nextId++);
        vehiculos.add(nuevo);
        return nuevo;
    }

    // Actualizar vehículo existente
    public Vehiculo update(long id, Vehiculo actualizado) {
        return vehiculos.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .map(v -> {
                    v.setPatente(actualizado.getPatente());
                    v.setMarca(actualizado.getMarca());
                    v.setModelo(actualizado.getModelo());
                    v.setAnio(actualizado.getAnio());
                    v.setRutCliente(actualizado.getRutCliente());
                    v.setEnReparacion(actualizado.isEnReparacion());
                    return v;
                })
                .orElse(null);
    }

    // Eliminar por ID
    public boolean deleteById(long id) {
        return vehiculos.removeIf(v -> v.getId() == id);
    }

    // Buscar por marca
    public List<Vehiculo> findByMarca(String marca) {
        return vehiculos.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase(marca))
                .toList();
    }

    // Buscar vehículos en reparación
    public List<Vehiculo> findEnReparacion() {
        return vehiculos.stream()
                .filter(Vehiculo::isEnReparacion)
                .toList();
    }
}
