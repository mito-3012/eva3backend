package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.MensajeContacto;
import com.example.eva3fullstack.repository.MensajeContactoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MensajeContactoService {

    private final MensajeContactoRepository repository;

    public MensajeContactoService(MensajeContactoRepository repository) {
        this.repository = repository;
    }

    public List<MensajeContacto> listarTodos() {
        return repository.findAll();
    }

    public MensajeContacto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "MensajeContacto no encontrado"));
    }

    public MensajeContacto crear(MensajeContacto entidad) {
        return repository.save(entidad);
    }

    public MensajeContacto actualizar(Long id, MensajeContacto datos) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "MensajeContacto no encontrado");
        }
        datos.setIdMensaje(id);
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "MensajeContacto no encontrado");
        }
        repository.deleteById(id);
    }
}
