package com.example.eva3fullstack.controller;

import com.example.eva3fullstack.entity.Cupon;
import com.example.eva3fullstack.service.CuponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cupones")
@CrossOrigin(origins = "http://localhost:5173")
public class CuponController {

    private final CuponService service;

    public CuponController(CuponService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cupon>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cupon> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cupon> crear(@RequestBody Cupon entidad) {
        Cupon nuevo = service.crear(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cupon> actualizar(
            @PathVariable Long id,
            @RequestBody Cupon entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
