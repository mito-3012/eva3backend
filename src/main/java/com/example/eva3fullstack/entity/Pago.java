
package com.example.eva3fullstack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @Column(nullable = false, length = 30)
    private String metodoPago;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false, length = 30)
    private String estado = "PENDIENTE";

    @Column(length = 120, unique = true)
    private String referenciaTransaccion;

    @Column(nullable = false, length = 3)
    private String moneda = "CLP";

    @Column(nullable = false)
    private LocalDateTime fechaPago = LocalDateTime.now();

    @Column(length = 500)
    private String observacion;
}
