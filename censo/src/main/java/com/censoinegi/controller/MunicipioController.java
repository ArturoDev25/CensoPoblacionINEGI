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

import com.censoinegi.model.Municipio;
import com.censoinegi.service.MunicipioService;

@RestController
@RequestMapping("/api/municipios")
@CrossOrigin(origins = "*")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public List<Municipio> getAll() {
        return municipioService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Municipio> getById(@PathVariable UUID id) {
        return municipioService.findById(id);
    }

    @PostMapping
    public Municipio create(@RequestBody Municipio municipio) {
        return municipioService.save(municipio);
    }

    @PutMapping("/{id}")
    public Municipio update(@PathVariable UUID id, @RequestBody Municipio municipio) {
        municipio.setId(id);
        return municipioService.save(municipio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        municipioService.deleteById(id);
    }
}
