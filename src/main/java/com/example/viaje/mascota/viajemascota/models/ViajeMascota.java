package com.example.viaje.mascota.viajemascota.models;


jakarta.persistence.entity;
jakarta.persistence.id;
jakarta.persistence.table;

import lombok.Data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Indexed;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

jakarta.validation.constrains.NotBlank;
jakarta.validation.constrains.NotNull;
jakarta.validation.constrains.Size;
jakarta.validation.constrains.Min;
jakarta.validation.constrains.Max;
jakarta.validation.constrains.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityScan
@Table(name = "viajemascota") //Falta agregar base de datos
public class ViajeMascota {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String nombremascota;
    private int edad;
    private String dueño;
    private String raza;
    private String genero;
}
