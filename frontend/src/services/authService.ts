import api from './apiService';
import type { LoginRequest } from '../types/LoginRequest';
import type { AuthResponse } from '../types/AuthResponse';
import { UsuarioRegistro } from '@/types/UsuarioRegistro';

export const AuthService = {
  login: async (credentials: LoginRequest): Promise<AuthResponse> => {
    const response = await api.post<AuthResponse>('/auth/login', credentials);
    if (response.data.token) {
      localStorage.setItem('token', response.data.token);
    }
    return response.data;
  },

  register: async (credentials: UsuarioRegistro): Promise<UsuarioRegistro> => {
    const response = await api.post<UsuarioRegistro>(
      '/auth/register',
      credentials,
    );
    return response.data;
  },

  logout: () => {
    localStorage.removeItem('token');
  },
};
