package com.upiiz.RestFull.assembler;

import com.upiiz.RestFull.controller.EntrenadorController;
import com.upiiz.RestFull.model.Entrenador;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EntrenadorModelAssembler implements RepresentationModelAssembler<Entrenador, EntityModel<Entrenador>> {

    @Override
    public EntityModel<Entrenador> toModel(Entrenador entrenador) {
        return EntityModel.of(entrenador,
                linkTo(methodOn(EntrenadorController.class).obtenerPorId(entrenador.getId())).withSelfRel(),
                linkTo(methodOn(EntrenadorController.class).listarEntrenadores()).withRel("entrenadores"),
                linkTo(methodOn(EntrenadorController.class).eliminarEntrenador(entrenador.getId())).withRel("delete")
        );
    }
}