package com.example.viaje.mascota.viajemascota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.viaje.mascota.viajemascota.models.ViajeMascota;

public interface ViajesRepository extends JpaRepository<ViajeMascota, Long> {
}