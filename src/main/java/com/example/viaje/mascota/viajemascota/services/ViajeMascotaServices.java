package com.example.viaje.mascota.viajemascota.services;

import org.springframework.stereotype.Service;
import com.example.viaje.mascota.viajemascota.models.ViajeMascota;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class ViajeMascotaServices {
    
    private List<ViajeMascota> viajeMascotas = new ArrayList<>();

    public ViajeMascotaServices() {
        viajeMascotas.add(new ViajeMascota(1L, "Pepe",10,"Pepe","Hombre","Masculino"));
        viajeMascotas.add(new ViajeMascota(2L,"Juan",12,"Pepe","Hombre","Masculino"));
        viajeMascotas.add(new ViajeMascota(3L,"Maria",8,"Pepe","Mujer","Femenino"));
        viajeMascotas.add(new ViajeMascota(4L,"Pedro",10,"Maria","Mujer","Femenino"));
    }

    public List<ViajeMascota> getViajeMascotas() {
        return viajeMascotas;
    }

    public Optional<ViajeMascota> getViajeMascota(Long id) {
        return viajeMascotas.stream().filter(viajeMascota -> viajeMascota.getId().equals(id)).findFirst();
    }
}
