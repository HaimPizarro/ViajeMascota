package com.example.viaje.mascota.viajemascota.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.viaje.mascota.viajemascota.models.ViajeMascota;
import com.example.viaje.mascota.viajemascota.services.ViajeMascotaServices;
import com.example.viaje.mascota.viajemascota.models.ResponseWrapper;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/viajemascota")
public class ViajeMascotaController {

    private final ViajeMascotaServices viajeMascotaServices;

    public ViajeMascotaController(ViajeMascotaServices viajeMascotaServices) {
        this.viajeMascotaServices = viajeMascotaServices;
    }

    @GetMapping
    public List<ViajeMascota> getViajeMascotas() {
        return viajeMascotaServices.getViajeMascotas();
    }

    @GetMapping("/{id}")
    public ViajeMascota getViajeMascotaById(@PathVariable Long id) {
        // Llamamos al servicio: getViajeMascotaById(id)
        return viajeMascotaServices.getViajeMascotaById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<ViajeMascota>> crearViaje(@Valid @RequestBody ViajeMascota viajeMascota) {
        // Usamos una variable distinta
        ViajeMascota nuevoViaje = viajeMascotaServices.crearViaje(viajeMascota);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>("Viaje creado con exito", 1, List.of(nuevoViaje)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<ViajeMascota>> actualizarViaje(
            @PathVariable Long id,
            @Valid @RequestBody ViajeMascota viajeMascota) {
        // Igual, variable distinta
        ViajeMascota viajeActualizado = viajeMascotaServices.actualizarViaje(id, viajeMascota);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseWrapper<>("Viaje actualizado con exito", 1, List.of(viajeActualizado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<ViajeMascota>> eliminarViaje(@PathVariable Long id) {
        ViajeMascota viajeEliminado = viajeMascotaServices.eliminarViaje(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseWrapper<>("Viaje eliminado con exito", 1, List.of(viajeEliminado)));
    }
}