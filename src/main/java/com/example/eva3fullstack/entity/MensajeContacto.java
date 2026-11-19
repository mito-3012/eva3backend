
package com.example.eva3fullstack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes_contacto")
@Getter
@Setter
@NoArgsConstructor
public class MensajeContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String correo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comentario;

    private LocalDateTime fechaEnvio = LocalDateTime.now();

    @Column(length = 30)
    private String estado = "PENDIENTE";
}
