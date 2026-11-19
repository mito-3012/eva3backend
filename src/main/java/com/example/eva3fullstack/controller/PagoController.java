
package com.example.eva3fullstack.controller;

import com.example.eva3fullstack.entity.Pago;
import com.example.eva3fullstack.service.PagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "http://localhost:5173")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        return ResponseEntity.ok(pagoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                pagoService.obtenerPorId(id)
        );
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<Pago>> listarPorPedido(
            @PathVariable Long idPedido
    ) {
        return ResponseEntity.ok(
                pagoService.listarPorPedido(idPedido)
        );
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pago>> listarPorEstado(
            @PathVariable String estado
    ) {
        return ResponseEntity.ok(
                pagoService.listarPorEstado(estado)
        );
    }

    @PostMapping
    public ResponseEntity<Pago> crear(
            @RequestBody Pago pago
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagoService.crear(pago));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(
            @PathVariable Long id,
            @RequestBody Pago pago
    ) {
        return ResponseEntity.ok(
                pagoService.actualizar(id, pago)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
