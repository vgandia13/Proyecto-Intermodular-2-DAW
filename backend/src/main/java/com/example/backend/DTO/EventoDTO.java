package com.example.backend.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class EventoDTO {
    private Long id;
    @NotNull    
    private String nombre;
    private String descripcion;
    private String fecha; 
    private String ubicacion;
    private String imagenUrl;
    private Long categoriaId;
    @NotNull
    private Long organizadorId;
}
