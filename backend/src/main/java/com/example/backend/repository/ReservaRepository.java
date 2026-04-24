package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
