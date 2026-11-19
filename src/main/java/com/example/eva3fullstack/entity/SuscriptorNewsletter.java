
package com.example.eva3fullstack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "suscriptores_newsletter")
@Getter
@Setter
@NoArgsConstructor
public class SuscriptorNewsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSuscriptor;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    private LocalDateTime fechaSuscripcion = LocalDateTime.now();

    private Boolean estado = true;
}
