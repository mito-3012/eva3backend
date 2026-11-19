
package com.example.eva3fullstack.service;

import com.example.eva3fullstack.entity.Pago;
import com.example.eva3fullstack.repository.PagoRepository;
import com.example.eva3fullstack.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PagoService {

    private static final Set<String> ESTADOS_VALIDOS =
            Set.of("PENDIENTE", "APROBADO", "RECHAZADO", "ANULADO");

    private final PagoRepository pagoRepository;
    private final PedidoRepository pedidoRepository;

    public PagoService(PagoRepository pagoRepository,
                       PedidoRepository pedidoRepository) {
        this.pagoRepository = pagoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional(readOnly = true)
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pago obtenerPorId(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Pago no encontrado con id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Pago> listarPorPedido(Long idPedido) {
        if (!pedidoRepository.existsById(idPedido)) {
            throw new RecursoNoEncontradoException(
                    "Pedido no encontrado con id: " + idPedido);
        }
        return pagoRepository.findByPedido_IdPedido(idPedido);
    }

    @Transactional(readOnly = true)
    public List<Pago> listarPorEstado(String estado) {
        return pagoRepository.findByEstadoIgnoreCase(estado);
    }

    public Pago crear(Pago pago) {
        validar(pago);
        validarPedido(pago);
        normalizar(pago);
        validarReferenciaUnica(
                pago.getReferenciaTransaccion(), null
        );
        return pagoRepository.save(pago);
    }

    public Pago actualizar(Long id, Pago datos) {
        Pago existente = obtenerPorId(id);

        validar(datos);
        validarPedido(datos);
        normalizar(datos);
        validarReferenciaUnica(
                datos.getReferenciaTransaccion(), id
        );

        existente.setPedido(datos.getPedido());
        existente.setMetodoPago(datos.getMetodoPago());
        existente.setMonto(datos.getMonto());
        existente.setEstado(datos.getEstado());
        existente.setReferenciaTransaccion(
                datos.getReferenciaTransaccion()
        );
        existente.setMoneda(datos.getMoneda());
        existente.setFechaPago(datos.getFechaPago());
        existente.setObservacion(datos.getObservacion());

        return pagoRepository.save(existente);
    }

    public void eliminar(Long id) {
        Pago existente = obtenerPorId(id);
        pagoRepository.delete(existente);
    }

    private void validar(Pago pago) {
        if (pago == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Los datos del pago son obligatorios."
            );
        }

        if (pago.getPedido() == null ||
                pago.getPedido().getIdPedido() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe indicar un pedido válido."
            );
        }

        if (pago.getMetodoPago() == null ||
                pago.getMetodoPago().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El método de pago es obligatorio."
            );
        }

        if (pago.getMonto() == null ||
                pago.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El monto debe ser mayor que cero."
            );
        }

        if (pago.getEstado() != null &&
                !ESTADOS_VALIDOS.contains(
                        pago.getEstado().trim().toUpperCase()
                )) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Estado de pago no válido."
            );
        }
    }

    private void validarPedido(Pago pago) {
        Long idPedido = pago.getPedido().getIdPedido();

        if (!pedidoRepository.existsById(idPedido)) {
            throw new RecursoNoEncontradoException(
                    "Pedido no encontrado con id: " + idPedido
            );
        }

        pago.setPedido(
                pedidoRepository.getReferenceById(idPedido)
        );
    }

    private void normalizar(Pago pago) {
        pago.setMetodoPago(
                pago.getMetodoPago().trim().toUpperCase()
        );

        pago.setEstado(
                pago.getEstado() == null ||
                        pago.getEstado().isBlank()
                        ? "PENDIENTE"
                        : pago.getEstado().trim().toUpperCase()
        );

        pago.setMoneda(
                pago.getMoneda() == null ||
                        pago.getMoneda().isBlank()
                        ? "CLP"
                        : pago.getMoneda().trim().toUpperCase()
        );

        if (pago.getFechaPago() == null) {
            pago.setFechaPago(
                    java.time.LocalDateTime.now()
            );
        }
    }

    private void validarReferenciaUnica(
            String referencia, Long idActual
    ) {
        if (referencia == null || referencia.isBlank()) {
            return;
        }

        pagoRepository.findByReferenciaTransaccion(referencia)
                .filter(p -> idActual == null ||
                        !idActual.equals(p.getIdPago()))
                .ifPresent(p -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "La referencia de transacción ya está registrada."
                    );
                });
    }
}
