export const Rol = {
  ROLE_VISITANTE: 'ROLE_VISITANTE',
  ROLE_ORGANIZADOR: 'ROLE_ORGANIZADOR',
  ROLE_EXPOSITOR: 'ROLE_EXPOSITOR',
  ROLE_ADMIN: 'ROLE_ADMIN',
} as const;

export type Rol = (typeof Rol)[keyof typeof Rol];
