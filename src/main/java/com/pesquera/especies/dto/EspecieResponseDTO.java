package com.pesquera.especies.dto;

public record EspecieResponseDTO(
    Long id,
    String nombre,
    Double cuotaAnualKg,
    Double cuotaDisponibleKg,
    Boolean vedaActiva,
    String zona
) {}