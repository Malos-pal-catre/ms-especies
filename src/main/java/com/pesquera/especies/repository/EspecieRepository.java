package com.pesquera.especies.repository;

import com.pesquera.especies.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EspecieRepository extends JpaRepository<Especie, Long> {

    // Query Methods
    Optional<Especie> findByNombre(String nombre);
    List<Especie> findByVedaActivaTrue();
    List<Especie> findByVedaActivaFalse();
    List<Especie> findByZona(String zona);

    // Custom Queries
    @Query("SELECT e FROM Especie e WHERE e.cuotaDisponibleKg > 0 AND e.vedaActiva = false")
    List<Especie> especiesDisponibles();

    @Query("SELECT e FROM Especie e WHERE e.cuotaDisponibleKg >= :kg AND e.vedaActiva = false")
    List<Especie> especiesConCuotaSuficiente(@Param("kg") Double kg);

    @Query(value = "SELECT * FROM especies WHERE zona = :zona AND veda_activa = false", nativeQuery = true)
    List<Especie> disponiblesPorZona(@Param("zona") String zona);
}