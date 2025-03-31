package com.example.viaje.mascota.viajemascota.models;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeMascota {
    private Long id;
    private String nombre;
    private int edad;
    private String dueño;
    private String raza;
    private String genero;
}
