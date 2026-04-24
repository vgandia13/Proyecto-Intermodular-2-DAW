package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    
}
