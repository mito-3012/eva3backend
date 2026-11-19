package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.Cupon;
import com.example.eva3fullstack.repository.CuponRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CuponService {

    private final CuponRepository repository;

    public CuponService(CuponRepository repository) {
        this.repository = repository;
    }

    public List<Cupon> listarTodos() {
        return repository.findAll();
    }

    public Cupon buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cupon no encontrado"));
    }

    public Cupon crear(Cupon entidad) {
        return repository.save(entidad);
    }

    public Cupon actualizar(Long id, Cupon datos) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cupon no encontrado");
        }
        datos.setIdCupon(id);
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cupon no encontrado");
        }
        repository.deleteById(id);
    }
}
