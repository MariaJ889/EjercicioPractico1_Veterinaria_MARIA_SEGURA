package com.caso01.veterinaria.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Column(name = "precio", nullable = false, precision = 8, scale = 2)
    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal precio;

    @Column(name = "duracion_minutos", nullable = false)
    @NotNull
    @Positive
    private Integer duracionMinutos;

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    @NotNull
    private TipoServicio tipoServicio;
}
