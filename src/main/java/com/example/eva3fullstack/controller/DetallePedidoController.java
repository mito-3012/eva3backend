package com.example.eva3fullstack.controller;

import com.example.eva3fullstack.entity.DetallePedido;
import com.example.eva3fullstack.service.DetallePedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
@CrossOrigin(origins = "http://localhost:5173")
public class DetallePedidoController {

    private final DetallePedidoService service;

    public DetallePedidoController(DetallePedidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DetallePedido>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DetallePedido> crear(@RequestBody DetallePedido entidad) {
        DetallePedido nuevo = service.crear(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizar(
            @PathVariable Long id,
            @RequestBody DetallePedido entidad) {
        return ResponseEntity.ok(service.actualizar(id, entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
