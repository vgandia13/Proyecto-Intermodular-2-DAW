package com.example.backend.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.DTO.EventoDTO;
import com.example.backend.model.Evento;
import com.example.backend.service.EventoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {
    
    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity<Page<EventoDTO>> obtenerTodos(
        @RequestParam(required = false) String nombre,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Evento> eventos;

        if (nombre != null && !nombre.isBlank()) {
            eventos = eventoRepository.findByNombreContainingIgnoreCase(nombre, pageable);
        } else {
            eventos = eventoRepository.findAll(pageable);
        }

        return ResponseEntity.ok(eventos);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ORGANIZADOR')")
    public EventoDTO crear(@RequestBody EventoDTO eventoDTO){
        return eventoService.guardar(eventoDTO);
    }

    @PostMapping("/{id}/asistir")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> asistir(@PathVariable Long id, Authentication authentication) {
        eventoService.inscribirUsuario(id, authentication.getName());
        return ResponseEntity.ok().build();
    }
}
