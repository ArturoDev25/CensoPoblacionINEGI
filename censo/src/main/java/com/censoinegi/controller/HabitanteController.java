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

import com.censoinegi.model.Habitante;
import com.censoinegi.service.HabitanteService;

@RestController
@RequestMapping("/api/habitantes")
@CrossOrigin(origins = "*")
public class HabitanteController {

    @Autowired
    private HabitanteService habitanteService;

    @GetMapping
    public List<Habitante> getAll() {
        return habitanteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Habitante> getById(@PathVariable UUID id) {
        return habitanteService.findById(id);
    }

    @PostMapping
    public Habitante create(@RequestBody Habitante habitante) {
        return habitanteService.save(habitante);
    }

    @PutMapping("/{id}")
    public Habitante update(@PathVariable String id, @RequestBody Habitante habitante) {
        habitante.setId(id);
        return habitanteService.save(habitante);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        habitanteService.deleteById(id);
    }
}
