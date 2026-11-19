package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.Producto;
import com.example.eva3fullstack.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    public Producto crear(Producto entidad) {
        return repository.save(entidad);
    }

    public Producto actualizar(Long id, Producto datos) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        datos.setIdProducto(id);
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        repository.deleteById(id);
    }
}
