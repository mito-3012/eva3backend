package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.Categoria;
import com.example.eva3fullstack.repository.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listarTodos() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Categoria no encontrado"));
    }

    public Categoria crear(Categoria entidad) {
        return repository.save(entidad);
    }

    public Categoria actualizar(Long id, Categoria datos) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Categoria no encontrado");
        }
        datos.setIdCategoria(id);
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Categoria no encontrado");
        }
        repository.deleteById(id);
    }
}
