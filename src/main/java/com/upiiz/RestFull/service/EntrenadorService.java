package com.upiiz.RestFull.service;

import com.upiiz.RestFull.exception.ResourceNotFoundException;
import com.upiiz.RestFull.model.Entrenador;
import com.upiiz.RestFull.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository repository;

    public List<Entrenador> findAll() {
        return repository.findAll();
    }

    public Entrenador findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el entrenador con ID: " + id));
    }

    public Entrenador save(Entrenador entrenador) {
        return repository.save(entrenador);
    }

    public Entrenador update(Long id, Entrenador detalles) {
        Entrenador entrenador = findById(id);
        entrenador.setNombre(detalles.getNombre());
        entrenador.setEspecialidad(detalles.getEspecialidad());
        entrenador.setExperiencia(detalles.getExperiencia());
        entrenador.setCorreo(detalles.getCorreo());
        return repository.save(entrenador);
    }

    public void deleteById(Long id) {
        Entrenador entrenador = findById(id);
        repository.delete(entrenador);
    }
}