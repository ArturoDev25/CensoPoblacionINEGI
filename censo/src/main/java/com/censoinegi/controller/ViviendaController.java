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

import com.censoinegi.model.Vivienda;
import com.censoinegi.service.ViviendaService;

@RestController
@RequestMapping("/api/viviendas")
@CrossOrigin(origins = "*")
public class ViviendaController {

    @Autowired
    private ViviendaService viviendaService;

    @GetMapping
    public List<Vivienda> getAll() {
        return viviendaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Vivienda> getById(@PathVariable Integer id) {
        return viviendaService.findById(id);
    }

    @PostMapping
    public Vivienda create(@RequestBody Vivienda vivienda) {
        return viviendaService.save(vivienda);
    }

    @PutMapping("/{id}")
    public Vivienda update(@PathVariable Integer id, @RequestBody Vivienda vivienda) {
        vivienda.setId(id);
        return viviendaService.save(vivienda);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        viviendaService.deleteById(id);
    }
}
