package com.example.vehiculo.service;

import com.example.vehiculo.dto.request.VehiculoRequestDTO;
import com.example.vehiculo.dto.response.VehiculoResponseDTO;
import com.example.vehiculo.model.Vehiculo;
import com.example.vehiculo.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marca esta clase como capa de lógica de negocio
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    /**
     * Obtiene todos los vehículos almacenados.
     *
     * @return lista de objetos VehiculoResponseDTO
     * @throws RuntimeException si ocurre un error durante la consulta
     */
    public List<VehiculoResponseDTO> obtenerTodos() {
        try {
            return vehiculoRepository.findAll()
                    .stream()
                    .map(this::toResponseDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener vehículos: " + e.getMessage());
        }
    }

    public VehiculoResponseDTO obtenerPorId(long id) {
        return vehiculoRepository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Vehículo con ID " + id + " no encontrado"));
    }

    public VehiculoResponseDTO guardar(VehiculoRequestDTO request) {
        Vehiculo vehiculo = Vehiculo.builder()
                .patente(request.getPatente())
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .anio(request.getAnio())
                .rutCliente(request.getRutCliente())
                .enReparacion(request.isEnReparacion())
                .build();

        Vehiculo guardado = vehiculoRepository.save(vehiculo);
        return toResponseDTO(guardado);
    }

    /**
     * Actualiza un vehículo existente según su id.
     *
     * @param id id del vehículo que se desea actualizar
     * @param request objeto VehiculoRequestDTO con los nuevos datos
     * @return el vehículo actualizado
     * @throws RuntimeException si ocurre un error durante la actualización
     */
    public VehiculoResponseDTO actualizar(long id, VehiculoRequestDTO request) {
        try {
            Vehiculo vehiculo = Vehiculo.builder()
                    .patente(request.getPatente())
                    .marca(request.getMarca())
                    .modelo(request.getModelo())
                    .anio(request.getAnio())
                    .rutCliente(request.getRutCliente())
                    .enReparacion(request.isEnReparacion())
                    .build();

            Vehiculo actualizado = vehiculoRepository.update(id, vehiculo);
            if (actualizado == null) {
                throw new RuntimeException("Vehículo con ID " + id + " no encontrado");
            }
            return toResponseDTO(actualizado);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar vehículo: " + e.getMessage());
        }
    }

    public void eliminar(long id) {
        boolean eliminado = vehiculoRepository.deleteById(id);
        if (!eliminado) {
            throw new RuntimeException("Vehículo con ID " + id + " no encontrado");
        }
    }

    public List<VehiculoResponseDTO> buscarPorMarca(String marca) {
        try {
            return vehiculoRepository.findByMarca(marca)
                    .stream()
                    .map(this::toResponseDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por marca: " + e.getMessage());
        }
    }

    public List<VehiculoResponseDTO> buscarEnReparacion() {
        try {
            return vehiculoRepository.findEnReparacion()
                    .stream()
                    .map(this::toResponseDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener vehículos en reparación: " + e.getMessage());
        }
    }

    // 🔄 Conversión de entidad a DTO
    private VehiculoResponseDTO toResponseDTO(Vehiculo v) {
        return new VehiculoResponseDTO(
                v.getId(),
                v.getPatente(),
                v.getMarca(),
                v.getModelo(),
                v.getAnio(),
                v.isEnReparacion()
        );
    }
}
