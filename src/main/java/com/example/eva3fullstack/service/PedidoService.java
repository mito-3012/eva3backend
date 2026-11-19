package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.Pedido;
import com.example.eva3fullstack.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido no encontrado"));
    }

    public Pedido crear(Pedido entidad) {
        return repository.save(entidad);
    }

    public Pedido actualizar(Long id, Pedido datos) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pedido no encontrado");
        }
        datos.setIdPedido(id);
        return repository.save(datos);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pedido no encontrado");
        }
        repository.deleteById(id);
    }
}
