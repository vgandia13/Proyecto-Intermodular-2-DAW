package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Puesto;

public interface PuestoRepository extends JpaRepository<Puesto, Long> {

}
