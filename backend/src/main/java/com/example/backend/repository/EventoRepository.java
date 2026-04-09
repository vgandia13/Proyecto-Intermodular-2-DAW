package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCategoriaId(Long categoriaId);
}
