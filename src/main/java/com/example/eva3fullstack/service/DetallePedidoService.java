package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.DetallePedido;
import com.example.eva3fullstack.repository.DetallePedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository repository;

    public DetallePedidoService(DetallePedidoRepository repository) {
        this.repository = repository;
    }

    public List<DetallePedido> listarTodos() {
        return repository.findAll();
    }

    public DetallePedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "DetallePedido no encontrado"));
    }

    public DetallePedido crear(DetallePedido entidad) {
        return repository.save(entidad);
    }

    public DetallePedido actualizar(Long id, DetallePedido datos) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "DetallePedido no encontrado");
        }
        datos.setIdDetallePedido(id);
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "DetallePedido no encontrado");
        }
        repository.deleteById(id);
    }
}
