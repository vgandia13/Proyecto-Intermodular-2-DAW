package com.example.backend.service;

import org.springframework.stereotype.Service;
import com.example.backend.repository.CategoriaRepository;
import com.example.backend.dto.CategoriaDTO;
import com.example.backend.model.Categoria;
import java.util.List;
import com.example.backend.exception.CategoriaNoEncontradaException;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> listarTodas(){
        return categoriaRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public CategoriaDTO obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new CategoriaNoEncontradaException("Categoría no encontrada"));
    }

    public CategoriaDTO guardar(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapToEntity(categoriaDTO);
        Categoria nuevaCategoria = categoriaRepository.save(categoria);
        return mapToDTO(nuevaCategoria);
    }

    private CategoriaDTO mapToDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        return dto;
    }

    private Categoria mapToEntity(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        return categoria;
    }
}
