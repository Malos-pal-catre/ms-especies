package com.pesquera.especies.dto;

import jakarta.validation.constraints.*;

public record EspecieRequestDTO(

    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotNull(message = "La cuota anual es obligatoria")
    @Positive(message = "La cuota anual debe ser mayor a 0")
    Double cuotaAnualKg,

    @NotNull(message = "La cuota disponible es obligatoria")
    @PositiveOrZero(message = "La cuota disponible no puede ser negativa")
    Double cuotaDisponibleKg,

    @NotNull(message = "El estado de veda es obligatorio")
    Boolean vedaActiva,

    @NotBlank(message = "La zona es obligatoria")
    String zona
) {}