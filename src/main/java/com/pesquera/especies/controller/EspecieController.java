package com.pesquera.especies.controller;

import com.pesquera.especies.dto.*;
import com.pesquera.especies.service.EspecieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especies")
@RequiredArgsConstructor
public class EspecieController {

    private final EspecieService especieService;

    @GetMapping
    public ResponseEntity<List<EspecieResponseDTO>> listarTodas() {
        return ResponseEntity.ok(especieService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecieResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(especieService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EspecieResponseDTO> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(especieService.buscarPorNombre(nombre));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<EspecieResponseDTO>> listarDisponibles() {
        return ResponseEntity.ok(especieService.listarDisponibles());
    }

    @GetMapping("/veda")
    public ResponseEntity<Boolean> tieneVedaActiva(@RequestParam String nombre) {
        return ResponseEntity.ok(especieService.tieneVedaActiva(nombre));
    }

    @GetMapping("/con-veda")
    public ResponseEntity<List<EspecieResponseDTO>> listarConVeda() {
        return ResponseEntity.ok(especieService.listarConVeda());
    }

    @PostMapping
    public ResponseEntity<EspecieResponseDTO> registrar(@RequestBody @Valid EspecieRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especieService.registrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecieResponseDTO> actualizar(@PathVariable Long id,
                                                          @RequestBody @Valid EspecieRequestDTO dto) {
        return ResponseEntity.ok(especieService.actualizar(id, dto));
    }

    @PatchMapping("/{id}/descontar-cuota")
    public ResponseEntity<EspecieResponseDTO> descontarCuota(@PathVariable Long id,
                                                              @RequestParam Double kg) {
        return ResponseEntity.ok(especieService.descontarCuota(id, kg));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        especieService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}