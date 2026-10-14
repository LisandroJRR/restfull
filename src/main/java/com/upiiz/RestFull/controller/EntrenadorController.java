package com.upiiz.RestFull.controller;

import com.upiiz.RestFull.assembler.EntrenadorModelAssembler;
import com.upiiz.RestFull.model.Entrenador;
import com.upiiz.RestFull.service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/entrenadores")
@Tag(name = "Entrenadores", description = "API RESTful de Entrenadores con Arquitectura por Capas, Validaciones y HATEOAS")
public class EntrenadorController {

    @Autowired
    private EntrenadorService service;

    @Autowired
    private EntrenadorModelAssembler assembler;

    @Operation(summary = "Listar todos los entrenadores")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Entrenador>>> listarEntrenadores() {
        List<EntityModel<Entrenador>> entrenadores = service.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Entrenador>> collectionModel = CollectionModel.of(entrenadores,
                linkTo(methodOn(EntrenadorController.class).listarEntrenadores()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Obtener un entrenador por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Entrenador>> obtenerPorId(@PathVariable Long id) {
        Entrenador entrenador = service.findById(id);
        return ResponseEntity.ok(assembler.toModel(entrenador));
    }

    @Operation(summary = "Crear un nuevo entrenador")
    @PostMapping
    public ResponseEntity<EntityModel<Entrenador>> crearEntrenador(@Valid @RequestBody Entrenador entrenador) {
        Entrenador nuevo = service.save(entrenador);
        return ResponseEntity.status(201).body(assembler.toModel(nuevo));
    }

    @Operation(summary = "Actualizar un entrenador existente")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Entrenador>> actualizarEntrenador(@PathVariable Long id, @Valid @RequestBody Entrenador detalles) {
        Entrenador actualizado = service.update(id, detalles);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @Operation(summary = "Eliminar un entrenador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}