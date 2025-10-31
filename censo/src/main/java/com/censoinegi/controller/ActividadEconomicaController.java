package com.censoinegi.controller;

import java.util.List;
import java.util.Optional;

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

import com.censoinegi.model.ActividadEconomica;
import com.censoinegi.service.ActividadEconomicaService;

@RestController
@RequestMapping("/api/actividades-economicas")
@CrossOrigin(origins = "*")
public class ActividadEconomicaController {

    @Autowired
    private ActividadEconomicaService actividadEconomicaService;

    @GetMapping
    public List<ActividadEconomica> getAll() {
        return actividadEconomicaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ActividadEconomica> getById(@PathVariable Integer id) {
        return actividadEconomicaService.findById(id);
    }

    @PostMapping
    public ActividadEconomica create(@RequestBody ActividadEconomica actividad) {
        return actividadEconomicaService.save(actividad);
    }

    @PutMapping("/{id}")
    public ActividadEconomica update(@PathVariable Integer id, @RequestBody ActividadEconomica actividad) {
        actividad.setId(id);
        return actividadEconomicaService.save(actividad);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        actividadEconomicaService.deleteById(id);
    }
}
