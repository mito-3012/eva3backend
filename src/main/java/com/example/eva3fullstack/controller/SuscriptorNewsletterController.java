package com.example.eva3fullstack.controller;

import com.example.eva3fullstack.entity.SuscriptorNewsletter;
import com.example.eva3fullstack.service.SuscriptorNewsletterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suscriptores-newsletter")
@CrossOrigin(origins = "http://localhost:5173")
public class SuscriptorNewsletterController {

    private final SuscriptorNewsletterService service;

    public SuscriptorNewsletterController(SuscriptorNewsletterService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SuscriptorNewsletter>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuscriptorNewsletter> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<SuscriptorNewsletter> crear(@RequestBody SuscriptorNewsletter entidad) {
        SuscriptorNewsletter nuevo = service.crear(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuscriptorNewsletter> actualizar(
            @PathVariable Long id,
            @RequestBody SuscriptorNewsletter entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
