package com.example.viaje.mascota.viajemascota;

import com.example.viaje.mascota.viajemascota.models.ViajeMascota;
import com.example.viaje.mascota.viajemascota.services.ViajeMascotaServices;
import com.example.viaje.mascota.viajemascota.repository.ViajesRepository;
import com.example.viaje.mascota.viajemascota.exceptions.ViajesNotFound;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; 
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViajeMascotaServiceTest {

    @Mock
    private ViajesRepository repo;

    @InjectMocks
    private ViajeMascotaServices service;

    private ViajeMascota demo() {
        return new ViajeMascota(1L, "Copito", 2, "Ana", "Mestizo", "H", 10000);
    }

    @Test
    void getViajeMascotas_returnsList() {
        when(repo.findAll()).thenReturn(List.of(demo()));

        List<ViajeMascota> res = service.getViajeMascotas();

        assertThat(res)
                .hasSize(1)
                .first()
                .satisfies(v -> assertThat(v.getNombremascota()).isEqualTo("Copito"));

        verify(repo).findAll();
    }

    @Test
    void getViajeMascotaById_exists_ok() {
        when(repo.findById(1L)).thenReturn(Optional.of(demo()));

        ViajeMascota v = service.getViajeMascotaById(1L);

        assertThat(v.getDuenio()).isEqualTo("Ana");
    }

    @Test
    void getViajeMascotaById_notFound_throws() {
        when(repo.findById(42L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getViajeMascotaById(42L))
                .isInstanceOf(ViajesNotFound.class);
    }

    @Test
    void crearViaje_idDuplicado_throws() {
        ViajeMascota v = demo();
        when(repo.existsById(1L)).thenReturn(true);

        assertThatThrownBy(() -> service.crearViaje(v))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
