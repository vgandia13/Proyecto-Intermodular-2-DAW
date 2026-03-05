package com.example.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Categoria;

@Repository 
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
