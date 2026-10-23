package com.caso01.veterinaria.Service;

import com.caso01.veterinaria.Domain.Servicio;
import com.caso01.veterinaria.Repository.ServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioService {

    private final ServicioRepository servicioRepository;

    @Transactional(readOnly = true)
    public List<Servicio> getServicios() {
        return servicioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Servicio> getServicio(Integer id) {
        return servicioRepository.findById(id);
    }

    @Transactional
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Transactional
    public void delete(Integer id) {
        if (!servicioRepository.existsById(id)) {
            throw new IllegalArgumentException("El servicio con ID " + id + " no existe.");
        }

        try {
            servicioRepository.deleteById(id);
            servicioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el servicio porque tiene citas asociadas.", e);
        }
    }
}
