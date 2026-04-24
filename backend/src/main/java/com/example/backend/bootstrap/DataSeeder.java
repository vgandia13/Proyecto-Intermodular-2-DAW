package com.example.backend.bootstrap;

import com.example.backend.model.Evento;
import com.example.backend.model.Rol;
import com.example.backend.model.Usuario;
import com.example.backend.repository.EventoRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(EventoRepository eventoRepository, 
                      UsuarioRepository usuarioRepository, 
                      PasswordEncoder passwordEncoder) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solo insertamos datos si la tabla de eventos está vacía
        if (eventoRepository.count() == 0) {
            
            // 1. Crear un usuario organizador (Requisito por la relación @ManyToOne)
            Usuario organizador = new Usuario();
            organizador.setNombre("Ayuntamiento Test");
            organizador.setEmail("admin@feriaplus.com");
            organizador.setPassword(passwordEncoder.encode("123456"));
            organizador.setRol(Rol.ROLE_ORGANIZADOR); // Asegúrate de que el enum Rol tenga este valor
            
            // Guardamos el usuario para que se le asigne un ID en la BD
            usuarioRepository.save(organizador);

            // 2. Crear eventos ficticios con imágenes reales (de Unsplash) para probar el frontend
            Evento e1 = new Evento(null, "Feria de Artesanía Local", 
                    "La mejor feria de la ciudad con productos 100% hechos a mano por artesanos de la comarca.", 
                    LocalDate.now().plusDays(5), "Plaza Mayor", 
                    "https://images.unsplash.com/photo-1531058020387-3be344556be6?auto=format&fit=crop&w=800&q=80", 
                    null, organizador, null);

            Evento e2 = new Evento(null, "Mercado Gastronómico", 
                    "Degusta los mejores platos típicos, quesos y vinos de la región en un ambiente familiar.", 
                    LocalDate.now().plusDays(12), "Parque Central", 
                    "https://images.unsplash.com/photo-1533900298318-6b8da08a523e?auto=format&fit=crop&w=800&q=80", 
                    null, organizador, null);

            Evento e3 = new Evento(null, "Feria del Libro Antiguo", 
                    "Encuentra joyas literarias, primeras ediciones y cómics descatalogados.", 
                    LocalDate.now().plusDays(20), "Paseo Marítimo", 
                    "https://images.unsplash.com/photo-1507842217343-583bb7270b66?auto=format&fit=crop&w=800&q=80", 
                    null, organizador, null);

            Evento e4 = new Evento(null, "Encuentro de Tejedores", 
                    "Talleres en vivo y venta exclusiva de prendas textiles sostenibles.", 
                    LocalDate.now().plusDays(25), "Centro Cívico", 
                    "https://images.unsplash.com/photo-1605289982774-9a6fef564df8?auto=format&fit=crop&w=800&q=80", 
                    null, organizador, null);

            Evento e5 = new Evento(null, "Mercadillo de Segunda Mano", 
                    "Dale una segunda vida a la ropa y accesorios. Sostenibilidad y economía circular.", 
                    LocalDate.now().plusDays(30), "Recinto Ferial", 
                    "https://images.unsplash.com/photo-1528698827591-e19ccd7bc23d?auto=format&fit=crop&w=800&q=80", 
                    null, organizador, null);

            // Guardar todos los eventos de golpe
            eventoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5));

            System.out.println("✅ Base de datos poblada con éxito: 1 Organizador y 5 Eventos de prueba.");
        }
    }
}
