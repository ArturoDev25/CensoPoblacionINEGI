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

import com.censoinegi.model.TipoVivienda;
import com.censoinegi.service.TipoViviendaService;

@RestController
@RequestMapping("/api/tipos-vivienda")
@CrossOrigin(origins = "*")
public class TipoViviendaController {

    @Autowired
    private TipoViviendaService tipoViviendaService;

    @GetMapping
    public List<TipoVivienda> getAll() {
        return tipoViviendaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TipoVivienda> getById(@PathVariable Integer id) {
        return tipoViviendaService.findById(id);
    }

    @PostMapping
    public TipoVivienda create(@RequestBody TipoVivienda tipo) {
        return tipoViviendaService.save(tipo);
    }

    @PutMapping("/{id}")
    public TipoVivienda update(@PathVariable Integer id, @RequestBody TipoVivienda tipo) {
        tipo.setId(id);
        return tipoViviendaService.save(tipo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        tipoViviendaService.deleteById(id);
    }
}
