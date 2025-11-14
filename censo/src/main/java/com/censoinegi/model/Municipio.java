package com.censoinegi.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipios")
public class Municipio {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;
    private String claveInegi;

    // --------------------------------------------------------------
    // 🔥 AGREGADO: Relaciones 1-N para que se muestren localidades y viviendas
    // --------------------------------------------------------------

    @OneToMany(mappedBy = "municipio")
    private List<Localidad> localidades;   // <-- agregado

    @OneToMany(mappedBy = "municipio")
    private List<Vivienda> viviendas;      // <-- agregado

    // --------------------------------------------------------------

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClaveInegi() {
        return claveInegi;
    }

    public void setClaveInegi(String claveInegi) {
        this.claveInegi = claveInegi;
    }

    // Getters agregados para relaciones
    public List<Localidad> getLocalidades() {   // <-- agregado
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {   // <-- agregado
        this.localidades = localidades;
    }

    public List<Vivienda> getViviendas() {   // <-- agregado
        return viviendas;
    }

    public void setViviendas(List<Vivienda> viviendas) {  // <-- agregado
        this.viviendas = viviendas;
    }
}
