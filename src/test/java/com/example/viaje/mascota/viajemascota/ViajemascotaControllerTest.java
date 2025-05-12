package com.example.viaje.mascota.viajemascota;

import com.example.viaje.mascota.viajemascota.controller.ViajeMascotaController;
import com.example.viaje.mascota.viajemascota.exceptions.ViajesNotFound;
import com.example.viaje.mascota.viajemascota.models.ViajeMascota;
import com.example.viaje.mascota.viajemascota.services.ViajeMascotaServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ViajeMascotaController.class)
class ViajeMascotaControllerTest {

    @Autowired
    private MockMvc mvc;

    @SuppressWarnings("removal")
    @MockBean
    private ViajeMascotaServices service;

    @Test
    void getById_ok() throws Exception {
        ViajeMascota demo = new ViajeMascota(
                1L,"Pelusa",4,"Benja","Beagle","M",20000);

        when(service.getViajeMascotaById(1L)).thenReturn(demo);

        mvc.perform(get("/viajemascota/1").accept(MediaTypes.HAL_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id").value(1))
           .andExpect(jsonPath("$.nombremascota").value("Pelusa"))
           .andExpect(jsonPath("$.valor").value(20000));
    }

    @Test
    void getById_notFound_returns404() throws Exception {
        when(service.getViajeMascotaById(99L))
                .thenThrow(new ViajesNotFound(99L));

        mvc.perform(get("/viajemascota/99").accept(MediaTypes.HAL_JSON))
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("$.message")
                      .value("No se ha encontrado el viaje con id: 99"));
    }

    @Test
    void crearViaje_ok() throws Exception {
        ViajeMascota guardado = new ViajeMascota(
                10L,"Luna",1,"Eva","Labrador","H",25000);

        when(service.crearViaje(any(ViajeMascota.class)))
                .thenReturn(guardado);

        String body = """
            {
              "nombremascota": "Luna",
              "edad": 1,
              "duenio": "Eva",
              "raza": "Labrador",
              "genero": "H",
              "valor": 25000
            }""";

        mvc.perform(post("/viajemascota")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.status").value("Viaje creado con exito"))
            .andExpect(jsonPath("$.data[0].id").value(10))
            .andExpect(jsonPath("$.data[0].nombremascota").value("Luna"));
    }
}