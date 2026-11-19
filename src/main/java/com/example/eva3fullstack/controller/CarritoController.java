package com.example.eva3fullstack.controller;

import com.example.eva3fullstack.entity.Carrito;
import com.example.eva3fullstack.service.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "http://localhost:5173")
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Carrito>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Carrito> crear(@RequestBody Carrito entidad) {
        Carrito nuevo = service.crear(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizar(
            @PathVariable Long id,
            @RequestBody Carrito entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
