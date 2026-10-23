package com.caso01.veterinaria.Service;

import com.caso01.veterinaria.Domain.Cita;
import com.caso01.veterinaria.Repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;

    @Transactional(readOnly = true)
    public List<Cita> getCitas() {
        return citaRepository.findAll();
    }
}
