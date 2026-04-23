package com.example.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.backend.repository.UsuarioRepository;
import com.example.backend.model.Usuario;
import com.example.backend.DTO.UsuarioRegistroDTO;
import com.example.backend.DTO.UsuarioResponseDTO;
import com.example.backend.exception.EmailRegistradoException;
import com.example.backend.exception.UsuarioNoEncontradoException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioResponseDTO registrarUsuario(UsuarioRegistroDTO registroDTO){
        if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
            throw new EmailRegistradoException("El email ya está registrado");
        }

        Usuario usuario = mapToEntity(registroDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return mapToResponseDTO(savedUsuario);
    }

    public UsuarioResponseDTO obtenerPerfilPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        return mapToResponseDTO(usuario);
    }

    public UsuarioRegistroDTO obtenerUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        return mapToResponseDTO(usuario);
    }

    public UsuarioResponseDTO actualizarPerfil(Long id, UsuarioRegistroDTO actualizacionDTO){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        if (!usuario.getEmail().equals(actualizacionDTO.getEmail()) && usuarioRepository.existsByEmail(actualizacionDTO.getEmail())) {
            throw new EmailRegistradoException("El email ya está registrado");
        }
        usuario.setNombre(actualizacionDTO.getNombre());
        usuario.setEmail(actualizacionDTO.getEmail());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return mapToResponseDTO(updatedUsuario);
    }

    private UsuarioResponseDTO mapToResponseDTO(Usuario usuario) {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(usuario.getId());
        responseDTO.setNombre(usuario.getNombre());
        responseDTO.setEmail(usuario.getEmail());
        responseDTO.setRol(usuario.getRol());
        responseDTO.setFechaRegistro(usuario.getFechaRegistro());
        return responseDTO;
    }

    private Usuario mapToEntity(UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        usuario.setRol(registroDTO.getRol());
        return usuario;
    }
}
