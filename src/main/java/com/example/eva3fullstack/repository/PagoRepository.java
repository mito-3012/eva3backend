
package com.example.eva3fullstack.repository;

import com.example.eva3fullstack.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByPedido_IdPedido(Long idPedido);

    List<Pago> findByEstadoIgnoreCase(String estado);

    Optional<Pago> findByReferenciaTransaccion(
            String referenciaTransaccion
    );
}
