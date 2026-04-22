import api from './api';
import { EventoDTO } from '../types'; // Tu interfaz TypeScript

export const EventoService = {
  // Público según tu SecurityConfig
  getAll: async (): Promise<EventoDTO[]> => {
    const response = await api.get<EventoDTO[]>('/eventos');
    return response.data;
  },

  // Requiere Rol ORGANIZADOR según tu SecurityConfig
  create: async (evento: EventoDTO): Promise<EventoDTO> => {
    const response = await api.post<EventoDTO>('/eventos', evento);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/eventos/${id}`);
  },
};
