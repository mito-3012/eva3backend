package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.DetalleCarrito;
import com.example.eva3fullstack.repository.DetalleCarritoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DetalleCarritoService {

    private final DetalleCarritoRepository repository;

    public DetalleCarritoService(DetalleCarritoRepository repository) {
        this.repository = repository;
    }

    public List<DetalleCarrito> listarTodos() {
        return repository.findAll();
    }

    public DetalleCarrito buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "DetalleCarrito no encontrado"));
    }

    public DetalleCarrito crear(DetalleCarrito entidad) {
        return repository.save(entidad);
    }

    public DetalleCarrito actualizar(Long id, DetalleCarrito datos) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "DetalleCarrito no encontrado");
        }
        datos.setIdDetalleCarrito(id);
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "DetalleCarrito no encontrado");
        }
        repository.deleteById(id);
    }
}
