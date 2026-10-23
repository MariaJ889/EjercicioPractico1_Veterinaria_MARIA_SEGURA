package com.caso01.veterinaria.Repository;

import com.caso01.veterinaria.Domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
