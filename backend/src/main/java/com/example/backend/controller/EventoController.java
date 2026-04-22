package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.EventoDTO;
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
    public List<EventoDTO> obtenerTodos() {
        return eventoService.listarTodos();
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
