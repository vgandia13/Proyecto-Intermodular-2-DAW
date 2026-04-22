import type { Rol } from './Rol';

export interface UsuarioRegistro {
  nombre: string;
  email: string;
  password: string;
  rol?: Rol;
}
