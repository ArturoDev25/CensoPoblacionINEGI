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

import com.censoinegi.model.AdminRegistro;
import com.censoinegi.service.AdminRegistroService;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*")
public class AdminRegistroController {

    @Autowired
    private AdminRegistroService service;

    @GetMapping
    public List<AdminRegistro> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AdminRegistro> getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public AdminRegistro create(@RequestBody AdminRegistro admin) {
        return service.save(admin);
    }

    @PutMapping("/{id}")
    public AdminRegistro update(@PathVariable UUID id, @RequestBody AdminRegistro admin) {
        admin.setId(id);
        return service.save(admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
