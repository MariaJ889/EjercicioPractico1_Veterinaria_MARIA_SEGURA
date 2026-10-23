package com.caso01.veterinaria.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tipo_servicio")
public class TipoServicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    @Size(max = 255)
    private String descripcion;

    @OneToMany(mappedBy = "tipoServicio")
    private List<Servicio> servicios = new ArrayList<>();
}
