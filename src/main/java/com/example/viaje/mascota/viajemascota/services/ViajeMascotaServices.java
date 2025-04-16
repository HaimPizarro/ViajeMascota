package com.example.viaje.mascota.viajemascota.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.viaje.mascota.viajemascota.models.ViajeMascota;
import com.example.viaje.mascota.viajemascota.repository.ViajesRepository;
import com.example.viaje.mascota.viajemascota.exceptions.ViajesNotFound;

@Service
public class ViajeMascotaServices {

    private final ViajesRepository viajesRepository;

    // El constructor debe llamarse igual que la clase
    public ViajeMascotaServices(ViajesRepository viajesRepository) {
        this.viajesRepository = viajesRepository;
    }

    public List<ViajeMascota> getViajeMascotas() {
        return viajesRepository.findAll();
    }

    public ViajeMascota getViajeMascotaById(Long id) {
        return viajesRepository.findById(id)
                .orElseThrow(() -> new ViajesNotFound(id));
    }

    public ViajeMascota crearViaje(ViajeMascota viajeMascota) {
        // Si ya existe un registro con ese ID, arrojamos excepción
        if (viajeMascota.getId() != null && viajesRepository.existsById(viajeMascota.getId())) {
            throw new IllegalArgumentException(
                "El viaje con id: " + viajeMascota.getId() + " ya existe");
        }
        return viajesRepository.save(viajeMascota);
    }

    public ViajeMascota actualizarViaje(Long id, ViajeMascota viajeMascota) {
        ViajeMascota viajeMascotaActual =
                viajesRepository.findById(id)
                        .orElseThrow(() -> new ViajesNotFound(id));

        viajeMascotaActual.setNombremascota(viajeMascota.getNombremascota());
        viajeMascotaActual.setEdad(viajeMascota.getEdad());
        viajeMascotaActual.setDueño(viajeMascota.getDueño());
        viajeMascotaActual.setRaza(viajeMascota.getRaza());
        viajeMascotaActual.setGenero(viajeMascota.getGenero());
        viajeMascotaActual.setValor(viajeMascota.getValor());

        return viajesRepository.save(viajeMascotaActual);
    }

    public ViajeMascota eliminarViaje(Long id) {
        ViajeMascota viajeMascota =
                viajesRepository.findById(id)
                        .orElseThrow(() -> new ViajesNotFound(id));
        viajesRepository.delete(viajeMascota);
        return viajeMascota; // Devuelve el que se eliminó
    }

}