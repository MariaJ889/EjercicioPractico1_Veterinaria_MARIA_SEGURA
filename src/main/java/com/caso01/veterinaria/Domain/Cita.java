package com.caso01.veterinaria.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cita")
public class Cita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre_mascota", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String nombreMascota;

    @Column(name = "nombre_propietario", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String nombrePropietario;

    @Column(name = "fecha", nullable = false)
    @NotNull
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    @NotNull
    private Servicio servicio;
}
