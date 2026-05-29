package com.pesquera.especies.dto;

import com.pesquera.especies.model.Especie;

public class EspecieMapper {

    public static EspecieResponseDTO toDTO(Especie e) {
        return new EspecieResponseDTO(
            e.getId(),
            e.getNombre(),
            e.getCuotaAnualKg(),
            e.getCuotaDisponibleKg(),
            e.getVedaActiva(),
            e.getZona()
        );
    }

    public static Especie toEntity(EspecieRequestDTO dto) {
        return Especie.builder()
            .nombre(dto.nombre())
            .cuotaAnualKg(dto.cuotaAnualKg())
            .cuotaDisponibleKg(dto.cuotaDisponibleKg())
            .vedaActiva(dto.vedaActiva())
            .zona(dto.zona())
            .build();
    }
}