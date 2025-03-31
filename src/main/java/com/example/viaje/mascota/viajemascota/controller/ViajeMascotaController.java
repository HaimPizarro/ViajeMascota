
package com.example.viaje.mascota.viajemascota.controller;


import com.example.viaje.mascota.viajemascota.models.ViajeMascota;
import com.example.viaje.mascota.viajemascota.services.ViajeMascotaServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


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
    public Optional<ViajeMascota> getViajeMascota(@PathVariable Long id) {
        return viajeMascotaServices.getViajeMascota(id);
    }

}
