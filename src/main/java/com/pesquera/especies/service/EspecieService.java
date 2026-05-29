package com.pesquera.especies.service;

import com.pesquera.especies.dto.*;
import com.pesquera.especies.exception.RecursoNoEncontradoException;
import com.pesquera.especies.model.Especie;
import com.pesquera.especies.repository.EspecieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecieService {

    private final EspecieRepository especieRepository;

    public List<EspecieResponseDTO> listarTodas() {
        return especieRepository.findAll()
            .stream()
            .map(EspecieMapper::toDTO)
            .toList();
    }

    public EspecieResponseDTO buscarPorId(Long id) {
        Especie e = especieRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Especie no encontrada con id: " + id));
        return EspecieMapper.toDTO(e);
    }

    public EspecieResponseDTO buscarPorNombre(String nombre) {
        Especie e = especieRepository.findByNombre(nombre)
            .orElseThrow(() -> new RecursoNoEncontradoException("Especie no encontrada: " + nombre));
        return EspecieMapper.toDTO(e);
    }

    public List<EspecieResponseDTO> listarDisponibles() {
        return especieRepository.especiesDisponibles()
            .stream()
            .map(EspecieMapper::toDTO)
            .toList();
    }

    public List<EspecieResponseDTO> listarConVeda() {
        return especieRepository.findByVedaActivaTrue()
            .stream()
            .map(EspecieMapper::toDTO)
            .toList();
    }

    public Boolean tieneVedaActiva(String nombre) {
        Especie e = especieRepository.findByNombre(nombre)
            .orElseThrow(() -> new RecursoNoEncontradoException("Especie no encontrada: " + nombre));
        return e.getVedaActiva();
    }

    public EspecieResponseDTO registrar(EspecieRequestDTO dto) {
        if (especieRepository.findByNombre(dto.nombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe la especie: " + dto.nombre());
        }
        return EspecieMapper.toDTO(especieRepository.save(EspecieMapper.toEntity(dto)));
    }

    public EspecieResponseDTO actualizar(Long id, EspecieRequestDTO dto) {
        Especie existente = especieRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Especie no encontrada con id: " + id));
        existente.setNombre(dto.nombre());
        existente.setCuotaAnualKg(dto.cuotaAnualKg());
        existente.setCuotaDisponibleKg(dto.cuotaDisponibleKg());
        existente.setVedaActiva(dto.vedaActiva());
        existente.setZona(dto.zona());
        return EspecieMapper.toDTO(especieRepository.save(existente));
    }

    public EspecieResponseDTO descontarCuota(Long id, Double kg) {
        Especie e = especieRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Especie no encontrada con id: " + id));
        if (e.getVedaActiva()) {
            throw new IllegalArgumentException("La especie tiene veda activa: " + e.getNombre());
        }
        if (e.getCuotaDisponibleKg() < kg) {
            throw new IllegalArgumentException("Cuota insuficiente para " + e.getNombre());
        }
        e.setCuotaDisponibleKg(e.getCuotaDisponibleKg() - kg);
        return EspecieMapper.toDTO(especieRepository.save(e));
    }

    public void eliminar(Long id) {
        if (!especieRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Especie no encontrada con id: " + id);
        }
        especieRepository.deleteById(id);
    }
}