export interface AuthResponse {
  token: string;
  usuario: {
    name: string;
    email: string;
    rol: string;
  }
}
