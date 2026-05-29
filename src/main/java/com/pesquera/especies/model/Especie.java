package com.pesquera.especies.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "especies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private Double cuotaAnualKg;

    @Column(nullable = false)
    private Double cuotaDisponibleKg;

    @Column(nullable = false)
    private Boolean vedaActiva;

    @Column(nullable = false)
    private String zona;
}