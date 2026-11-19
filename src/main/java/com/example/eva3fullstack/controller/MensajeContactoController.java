package com.example.eva3fullstack.controller;

import com.example.eva3fullstack.entity.MensajeContacto;
import com.example.eva3fullstack.service.MensajeContactoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes-contacto")
@CrossOrigin(origins = "http://localhost:5173")
public class MensajeContactoController {

    private final MensajeContactoService service;

    public MensajeContactoController(MensajeContactoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MensajeContacto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeContacto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MensajeContacto> crear(@RequestBody MensajeContacto entidad) {
        MensajeContacto nuevo = service.crear(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeContacto> actualizar(
            @PathVariable Long id,
            @RequestBody MensajeContacto entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
