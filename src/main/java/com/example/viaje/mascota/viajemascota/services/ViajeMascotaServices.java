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
        viajeMascotas.add(new ViajeMascota(1L,"Pepito",4,"Maria Luna","Pastor Aleman","Masculino"));
        viajeMascotas.add(new ViajeMascota(2L,"Copito",2,"Pedro Rivera","Belga","Masculino"));
        viajeMascotas.add(new ViajeMascota(3L,"Lazy",8,"Marecelo Vidal","Poddle","Femenino"));
        viajeMascotas.add(new ViajeMascota(4L,"Nicanora",10,"Maria Rodriguez","Pastor Aleman","Femenino"));
        viajeMascotas.add(new ViajeMascota(5L,"Thor",7,"Lucy Nuñez","Beagle","Masculino"));
    }

    public List<ViajeMascota> getViajeMascotas() {
        return viajeMascotas;
    }

    public Optional<ViajeMascota> getViajeMascota(Long id) {
        return viajeMascotas.stream().filter(viajeMascota -> viajeMascota.getId().equals(id)).findFirst();
    }
}
