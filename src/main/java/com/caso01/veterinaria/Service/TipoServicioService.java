package com.caso01.veterinaria.Service;

import com.caso01.veterinaria.Domain.TipoServicio;
import com.caso01.veterinaria.Repository.TipoServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;

    @Transactional(readOnly = true)
    public List<TipoServicio> getTiposServicio() {
        return tipoServicioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<TipoServicio> getTipoServicio(Integer id) {
        return tipoServicioRepository.findById(id);
    }

    @Transactional
    public TipoServicio save(TipoServicio tipoServicio) {
        return tipoServicioRepository.save(tipoServicio);
    }

    @Transactional
    public void delete(Integer id) {
        if (!tipoServicioRepository.existsById(id)) {
            throw new IllegalArgumentException("El tipo de servicio con ID " + id + " no existe.");
        }

        try {
            tipoServicioRepository.deleteById(id);
            tipoServicioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el tipo de servicio porque tiene servicios asociados.", e);
        }
    }
}
