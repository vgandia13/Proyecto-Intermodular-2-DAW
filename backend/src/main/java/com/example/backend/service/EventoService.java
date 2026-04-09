package com.example.backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.EventoDTO;
import com.example.backend.exception.UsuarioNoEncontradoException;
import com.example.backend.model.Evento;
import com.example.backend.model.Usuario;
import com.example.backend.repository.CategoriaRepository;
import com.example.backend.repository.EventoRepository;
import com.example.backend.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<EventoDTO> listarTodos() {
        return eventoRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<EventoDTO> listarPorCategoria(Long categoriaId) {
        return eventoRepository.findByCategoriaId(categoriaId).stream()
                .map(this::mapToDTO)
                .toList();
    }

    public EventoDTO guardar(EventoDTO eventoDTO) {
        Evento evento = mapToEntity(eventoDTO);
        Evento nuevoEvento = eventoRepository.save(evento);
        return mapToDTO(nuevoEvento);
    }

    public EventoDTO obtenerPorId(Long id) {
        return eventoRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    @Transactional
    public void inscribirUsuario(Long eventoId, String emailUsuario) {
        Evento e = eventoRepository.findById(eventoId).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        Usuario u = usuarioRepository.findByEmail(emailUsuario).orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        if( e != null && u != null){
            e.getAsistentes().add(u);
            eventoRepository.save(e);
        }
    }


    private EventoDTO mapToDTO(Evento evento) {
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setNombre(evento.getNombre());
        dto.setDescripcion(evento.getDescripcion());
        dto.setFecha(evento.getFecha() != null ? evento.getFecha().toString() : null);
        dto.setUbicacion(evento.getUbicacion());
        dto.setImagenUrl(evento.getImagenUrl());
        
        if(evento.getCategoria() != null){
            dto.setCategoriaId(evento.getCategoria().getId());
        }
        if(evento.getOrganizador() != null){
            dto.setOrganizadorId(evento.getOrganizador().getId());
        }
        return dto;
    }

    private Evento mapToEntity(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setId(dto.getId());
        evento.setNombre(dto.getNombre());
        evento.setDescripcion(dto.getDescripcion());
        evento.setFecha(LocalDate.parse(dto.getFecha()));
        evento.setUbicacion(dto.getUbicacion());
        evento.setImagenUrl(dto.getImagenUrl());

        if(dto.getCategoriaId() != null){
            evento.setCategoria(categoriaRepository.findById(dto.getCategoriaId()).orElse(null));
        }
        if(dto.getOrganizadorId() != null){
            evento.setOrganizador(usuarioRepository.findById(dto.getOrganizadorId()).orElse(null));
        }
        return evento;
    }
}
