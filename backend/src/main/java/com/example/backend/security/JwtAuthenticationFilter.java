package com.example.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    // Inyectamos nuestras herramientas
    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // Obtener el token del header "Authorization"
        String header = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7); // Quitamos "Bearer " para quedarnos solo con el código
            if(jwtUtils.validateToken(token)) {
                email = jwtUtils.getEmailFromToken(token);
            }else{
                logger.warn("Token inválido: " + token);
            }
        }

        //  Si hay email y el usuario no está ya autenticado en esta petición...
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            // Creamos el objeto de autenticación con los roles (Organizador, etc.)
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
                
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Establecemos al usuario como "autenticado" para el resto de la petición
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //  Continuar con el siguiente filtro
        filterChain.doFilter(request, response);
    }
}