package com.censoinegi.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.censoinegi.model.Localidad;
import com.censoinegi.service.LocalidadService;

@RestController
@RequestMapping("/api/localidades")
@CrossOrigin(origins = "*")
public class LocalidadController {

    @Autowired
    private LocalidadService localidadService;

    @GetMapping
    public List<Localidad> getAll() {
        return localidadService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Localidad> getById(@PathVariable UUID id) {
        return localidadService.findById(id);
    }

    @PostMapping
    public Localidad create(@RequestBody Localidad localidad) {
        return localidadService.save(localidad);
    }

    @PutMapping("/{id}")
    public Localidad update(@PathVariable UUID id, @RequestBody Localidad localidad) {
        localidad.setId(id);
        return localidadService.save(localidad);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        localidadService.deleteById(id);
    }
}
