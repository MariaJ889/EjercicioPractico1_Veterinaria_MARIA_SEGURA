package com.caso01.veterinaria.Repository;

import com.caso01.veterinaria.Domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
}
