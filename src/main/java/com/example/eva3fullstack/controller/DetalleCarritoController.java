package com.example.eva3fullstack.controller;

import com.example.eva3fullstack.entity.DetalleCarrito;
import com.example.eva3fullstack.service.DetalleCarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-carrito")
@CrossOrigin(origins = "http://localhost:5173")
public class DetalleCarritoController {

    private final DetalleCarritoService service;

    public DetalleCarritoController(DetalleCarritoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DetalleCarrito>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCarrito> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DetalleCarrito> crear(@RequestBody DetalleCarrito entidad) {
        DetalleCarrito nuevo = service.crear(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCarrito> actualizar(
            @PathVariable Long id,
            @RequestBody DetalleCarrito entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
