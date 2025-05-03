package com.example.viaje.mascota.viajemascota;

import com.example.viaje.mascota.viajemascota.controller.ViajeMascotaController;
import com.example.viaje.mascota.viajemascota.models.ViajeMascota;
import com.example.viaje.mascota.viajemascota.services.ViajeMascotaServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ViajeMascotaController.class)
class ViajeMascotaControllerTest {

    @Autowired
    private MockMvc mvc;

    @org.springframework.test.context.bean.override.mockito.MockitoBean
    private ViajeMascotaServices service;

    @Test
    void getById_ok() throws Exception {
        // Arrange
        ViajeMascota demo = new ViajeMascota(
                1L, "Pelusa", 4, "Benja", "Beagle", "M", 20000);
        when(service.getViajeMascotaById(1L)).thenReturn(demo);

        // Act + Assert
        mvc.perform(get("/viajemascota/1").accept(MediaTypes.HAL_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id").value(1))
           .andExpect(jsonPath("$.nombremascota").value("Pelusa"))
           .andExpect(jsonPath("$.valor").value(20000));
    }
}
