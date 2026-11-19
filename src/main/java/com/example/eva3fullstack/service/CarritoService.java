package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.Carrito;
import com.example.eva3fullstack.repository.CarritoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarritoService {

    private final CarritoRepository repository;

    public CarritoService(CarritoRepository repository) {
        this.repository = repository;
    }

    public List<Carrito> listarTodos() {
        return repository.findAll();
    }

    public Carrito buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Carrito no encontrado"));
    }

    public Carrito crear(Carrito entidad) {
        return repository.save(entidad);
    }

    public Carrito actualizar(Long id, Carrito datos) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }
        datos.setIdCarrito(id);
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }
        repository.deleteById(id);
    }
}
