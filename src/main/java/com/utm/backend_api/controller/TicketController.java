package com.utm.backend_api.controller;

import com.utm.backend_api.model.Ticket;
import com.utm.backend_api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    // GET /api/tickets: Listar todos los incidentes
    @GetMapping
    public List<Ticket> listarTodos() {
        return ticketRepository.findAll();
    }
    // GET /api/tickets/{id}: Obtener un incidente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerPorId(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .map(ticket -> new ResponseEntity<>(ticket, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST /api/tickets: Registrar un nuevo incidente
    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@RequestBody Ticket ticket) {
        Ticket nuevoTicket = ticketRepository.save(ticket);
        return new ResponseEntity<>(nuevoTicket, HttpStatus.CREATED);
    }
    // PUT /api/tickets/{id}: Actualizar un incidente existente
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizarTicket(@PathVariable Long id, @RequestBody Ticket ticketActualizado) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setTitulo(ticketActualizado.getTitulo());
                    ticket.setDescripcion(ticketActualizado.getDescripcion());
                    ticket.setCategoria(ticketActualizado.getCategoria());
                    ticket.setPrioridad(ticketActualizado.getPrioridad());
                    ticket.setEstado(ticketActualizado.getEstado());
                    Ticket ticketGuardado = ticketRepository.save(ticket);
                    return new ResponseEntity<>(ticketGuardado, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // DELETE /api/tickets/{id}: Eliminar un incidente existente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticketRepository.delete(ticket);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));   
    }
}
