package com.censoinegi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censoinegi.model.AdminRegistro;
import com.censoinegi.repository.AdminRegistroRepository;

@Service
public class AdminRegistroService {

    @Autowired
    private AdminRegistroRepository repository;

    public List<AdminRegistro> findAll() {
        return repository.findAll();
    }

    public Optional<AdminRegistro> findById(UUID id) {
        return repository.findById(id);
    }

    public AdminRegistro save(AdminRegistro adminRegistro) {
        return repository.save(adminRegistro);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
