export interface EventoDTO {
  id?: number;
  nombre: string;
  descripcion?: string;
  fecha: string;
  ubicacion?: string;
  latitud?: number;
  longitud?: number;
  imagenUrl?: string;
  categoriaId?: number;
  organizadorId: number;
}
