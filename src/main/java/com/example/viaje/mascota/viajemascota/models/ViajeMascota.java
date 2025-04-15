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
    @NotNull(message = "El campo id es obligatorio")
    @Positive(messaje= 'El id debe tener un valor positivo')
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min=1, max=100, message="El campo nombre debe tener entre 1 y 100 caracteres")
    private String nombremascota;

    @NotBlanc(mesage="El campo edad es obligatorio")
    @Positive(message="El campo edad debe tener un valor positivo")
    private int edad;

    @NotBlanc(message="El campo dueño es obligatorio")
    @Size(min=1, max=100, message="El campo debe tener entre 1 y 100 caracteres")
    private String dueño;

    @NotNull(message="El campo raza es obligatorio")
    @Size(min=1, max=100, message="El campo debe tener entre 1 y 100 caracteres")
    private String raza;

    @NotNull(message="El campo genero es obligatorio")
    private String genero;

    @NotNull(message="El campo valor es obligatorio")
    @Positive(message="El campo debe tener un valor positivo")
    @Size(min=1, max=40, message = "El campo debe tener entre 1 y 40 caracteres")
    private Int valor;

}
