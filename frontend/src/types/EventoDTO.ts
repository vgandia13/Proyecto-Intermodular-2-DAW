export interface EventoDTO {
  id?: number;
  nombre: string;
  descripcion?: string;
  fecha: string;
  ubicacion?: string;
  imagenUrl?: string;
  categoriaId?: number;
  organizadorId: number;
}
