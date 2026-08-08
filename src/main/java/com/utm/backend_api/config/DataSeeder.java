package com.utm.backend_api.config;

import com.utm.backend_api.model.Categoria;
import com.utm.backend_api.model.Estado;
import com.utm.backend_api.model.Prioridad;
import com.utm.backend_api.model.Ticket;
import com.utm.backend_api.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Siembra datos de prueba al iniciar la aplicación.
 * Solo inserta si la tabla está vacía, evitando duplicados
 * (útil porque H2 en memoria reinicia su contenido en cada deploy).
 */
@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedTickets(TicketRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }

            List<Ticket> muestras = List.of(
                    crearTicket("Pantalla azul al iniciar sesión",
                            "El equipo muestra BSOD tras el último parche de Windows. Revisar drivers.",
                            Categoria.SOFTWARE, Prioridad.ALTA, Estado.ABIERTO),
                    crearTicket("Problemas de conexión a red interna",
                            "Los usuarios no pueden acceder a la carpeta compartida del servidor.",
                            Categoria.RED, Prioridad.ALTA, Estado.EN_PROGRESO),
                    crearTicket("Mouse y teclado fallan",
                            "Los periféricos USB dejan de responder después de unos minutos.",
                            Categoria.HARDWARE, Prioridad.MEDIA, Estado.ABIERTO),
                    crearTicket("Actualización del sistema contable",
                            "Se aplicó parche, pendiente verificación final con el área de tesorería.",
                            Categoria.SOFTWARE, Prioridad.MEDIA, Estado.EN_PROGRESO),
                    crearTicket("Sin acceso a wifi en 2do piso",
                            "Los dispositivos móviles no detectan la red luego del reinicio del router.",
                            Categoria.RED, Prioridad.MEDIA, Estado.ABIERTO),
                    crearTicket("Impresora compartida notificada",
                            "La impresora de la recepción fue revisada y reemplazado el toner.",
                            Categoria.HARDWARE, Prioridad.BAJA, Estado.CERRADO),
                    crearTicket("Licencia de antivirus vencida",
                            "Varios equipos reportan la alerta de protección desactivada.",
                            Categoria.SOFTWARE, Prioridad.BAJA, Estado.ABIERTO),
                    crearTicket("Cables de red dañados",
                            "Se repusieron los cables dañados del área de bodega.",
                            Categoria.RED, Prioridad.BAJA, Estado.CERRADO));

            repository.saveAll(muestras);
        };
    }

    private Ticket crearTicket(String titulo, String descripcion,
                               Categoria categoria, Prioridad prioridad, Estado estado) {
        Ticket ticket = new Ticket();
        ticket.setTitulo(titulo);
        ticket.setDescripcion(descripcion);
        ticket.setCategoria(categoria);
        ticket.setPrioridad(prioridad);
        ticket.setEstado(estado);
        return ticket;
    }
}