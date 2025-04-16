package com.example.viaje.mascota.viajemascota.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "viajemascota")  // Asegúrate de que coincida con tu tabla real en la BD
public class ViajeMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min = 1, max = 100, message = "El campo nombre debe tener entre 1 y 100 caracteres")
    private String nombremascota;

    @NotNull(message = "El campo edad es obligatorio")
    @Positive(message = "El campo edad debe tener un valor positivo")
    private Integer edad;

    @NotBlank(message = "El campo dueño es obligatorio")
    @Size(min = 1, max = 100, message = "El campo dueño debe tener entre 1 y 100 caracteres")
    private String dueño;

    @NotBlank(message = "El campo raza es obligatorio")
    @Size(min = 1, max = 100, message = "El campo raza debe tener entre 1 y 100 caracteres")
    private String raza;

    @NotBlank(message = "El campo genero es obligatorio")
    private String genero;

    @NotNull(message = "El campo valor es obligatorio")
    @Positive(message = "El valor debe ser positivo")
    @Min(value = 1, message = "El valor debe ser al menos 1")
    @Max(value = 999999, message = "El valor no puede exceder 999999")
    private Integer valor;
}