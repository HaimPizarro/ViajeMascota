package com.example.viaje.mascota.viajemascota.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.viaje.mascota.viajemascota.controller.ViajeMascotaController;
import com.example.viaje.mascota.viajemascota.models.ViajeMascota;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component 
public class ViajeMascotaModelAssembler
        implements RepresentationModelAssembler<ViajeMascota, EntityModel<ViajeMascota>> {

    @Override
    public @NonNull EntityModel<ViajeMascota> toModel(@NonNull ViajeMascota viajeMascota) {

        return EntityModel.of(
                viajeMascota,

                // self
                linkTo(methodOn(ViajeMascotaController.class)
                        .getViajeMascotaById(viajeMascota.getId()))
                        .withSelfRel(),

                // update
                linkTo(methodOn(ViajeMascotaController.class)
                        .actualizarViaje(viajeMascota.getId(), viajeMascota))
                        .withRel("update"),

                // delete
                linkTo(methodOn(ViajeMascotaController.class)
                        .eliminarViaje(viajeMascota.getId()))
                        .withRel("delete"),

                // colección completa
                linkTo(methodOn(ViajeMascotaController.class)
                        .getViajeMascotas())
                        .withRel("collection")
        );
    }
}
