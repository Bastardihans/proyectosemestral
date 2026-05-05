package com.example.vehiculo.controller;

import com.example.vehiculo.dto.request.LoginRequestDTO;
import com.example.vehiculo.dto.response.LoginResponseDTO;
import com.example.vehiculo.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // más consistente con REST
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        // 🔑 Usuario quemado solo para pruebas
        if (!"mecanico".equals(request.getUsername()) || !"1234".equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Generar token JWT
        String token = jwtService.generarToken(request.getUsername());

        return ResponseEntity.ok(new LoginResponseDTO(token, "Bearer"));
    }
}
