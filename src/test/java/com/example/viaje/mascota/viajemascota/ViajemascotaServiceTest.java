package com.example.viaje.mascota.viajemascota;

import com.example.viaje.mascota.viajemascota.exceptions.ViajesNotFound;
import com.example.viaje.mascota.viajemascota.models.ViajeMascota;
import com.example.viaje.mascota.viajemascota.repository.ViajesRepository;
import com.example.viaje.mascota.viajemascota.services.ViajeMascotaServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViajeMascotaServiceTest {

    @Mock
    private ViajesRepository repo;

    @InjectMocks
    private ViajeMascotaServices service;

    /* ---------- DUMMY ---------- */
    private ViajeMascota demo() {
        return new ViajeMascota(1L, "Copito", 2, "Ana", "Mestizo", "H", 10000);
    }

    /* ---------- TEST ORIGINAL 1 ---------- */
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

    /* ---------- TEST ORIGINAL 2 ---------- */
    @Test
    void getViajeMascotaById_exists_ok() {
        when(repo.findById(1L)).thenReturn(Optional.of(demo()));

        ViajeMascota v = service.getViajeMascotaById(1L);

        assertThat(v.getDuenio()).isEqualTo("Ana");
    }

    /* ---------- TEST ORIGINAL 3 ---------- */
    @Test
    void getViajeMascotaById_notFound_throws() {
        when(repo.findById(42L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getViajeMascotaById(42L))
                .isInstanceOf(ViajesNotFound.class);
    }

    /* ---------- TEST ORIGINAL 4 ---------- */
    @Test
    void crearViaje_idDuplicado_throws() {
        ViajeMascota v = demo();
        when(repo.existsById(1L)).thenReturn(true);

        assertThatThrownBy(() -> service.crearViaje(v))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /* ---------- TEST NUEVO 3 ---------- */
    @Test
    void actualizarViaje_ok() {
        ViajeMascota existente = demo();                 // id = 1L
        ViajeMascota cambios   = new ViajeMascota(
                null, "Nube", 5, "Ana", "Poodle", "H", 12000);

        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        when(repo.save(any(ViajeMascota.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        ViajeMascota res = service.actualizarViaje(1L, cambios);

        assertThat(res.getId()).isEqualTo(1L);           // conserva id
        assertThat(res.getNombremascota()).isEqualTo("Nube");
        assertThat(res.getEdad()).isEqualTo(5);
    }

    /* ---------- TEST NUEVO 4 ---------- */
    @Test
    void eliminarViaje_notFound_throws() {
        when(repo.findById(42L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.eliminarViaje(42L))
                .isInstanceOf(ViajesNotFound.class);

        verify(repo, never()).delete(any());
    }
}