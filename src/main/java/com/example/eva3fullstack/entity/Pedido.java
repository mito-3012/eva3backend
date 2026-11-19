
package com.example.eva3fullstack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cupon")
    private Cupon cupon;

    private LocalDateTime fechaPedido = LocalDateTime.now();

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal descuento;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Column(length = 30)
    private String estado = "PENDIENTE";

    @Column(length = 255)
    private String direccionDespacho;
}
