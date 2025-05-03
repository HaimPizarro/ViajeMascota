package com.example.viaje.mascota.viajemascota;

import com.example.viaje.mascota.viajemascota.models.ViajeMascota;
import com.example.viaje.mascota.viajemascota.repository.ViajesRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ViajesRepositoryTest {

    @Autowired
    private ViajesRepository repo;

    @Test
    void saveAndFindById_ok() {
        ViajeMascota v = new ViajeMascota(
                null, "Firulais", 3, "Juan", "Poodle", "M", 15000);
        v = repo.save(v);

        assertThat(v.getId()).isNotNull();
        assertThat(repo.findById(v.getId()))
                .isPresent()
                .get()
                .extracting(ViajeMascota::getNombremascota)
                .isEqualTo("Firulais");
    }
}