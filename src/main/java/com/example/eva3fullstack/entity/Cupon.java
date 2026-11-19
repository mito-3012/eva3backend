package com.example.eva3fullstack.entity;

import jakarta.persistence.*;
        import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cupones")
@Getter
@Setter
@NoArgsConstructor
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCupon;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(length = 255)
    private String descripcion;

    @Column(precision = 5, scale = 2)
    private BigDecimal porcentajeDescuento;

    private LocalDate fechaInicio;

    private LocalDate fechaVencimiento;

    private Boolean estado = true;
}
