package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.SuscriptorNewsletter;
import com.example.eva3fullstack.repository.SuscriptorNewsletterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SuscriptorNewsletterService {

    private final SuscriptorNewsletterRepository repository;

    public SuscriptorNewsletterService(SuscriptorNewsletterRepository repository) {
        this.repository = repository;
    }

    public List<SuscriptorNewsletter> listarTodos() {
        return repository.findAll();
    }

    public SuscriptorNewsletter buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "SuscriptorNewsletter no encontrado"));
    }

    public SuscriptorNewsletter crear(SuscriptorNewsletter entidad) {
        return repository.save(entidad);
    }

    public SuscriptorNewsletter actualizar(Long id, SuscriptorNewsletter datos) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "SuscriptorNewsletter no encontrado");
        }
        datos.setIdSuscriptor(id);
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "SuscriptorNewsletter no encontrado");
        }
        repository.deleteById(id);
    }
}
