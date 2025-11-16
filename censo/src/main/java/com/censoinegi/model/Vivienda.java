package com.censoinegi.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "viviendas")
public class Vivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_vivienda_id")
    private TipoVivienda tipoVivienda;

    private String direccion;
    private String codigoPostal;
    private Integer numHabitaciones;
    private Integer numBanos;
    private Boolean tieneServiciosBasicos;
    private String materialPrincipal;
    private Integer numHabitantes;
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @ManyToMany
    @JoinTable(name = "vivienda_actividad", joinColumns = @JoinColumn(name = "vivienda_id"), inverseJoinColumns = @JoinColumn(name = "actividad_id"))
    private List<ActividadEconomica> actividades;

    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.ALL)
    private List<Habitante> habitantes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public TipoVivienda getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(TipoVivienda tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public Integer getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(Integer numBanos) {
        this.numBanos = numBanos;
    }

    public Boolean getTieneServiciosBasicos() {
        return tieneServiciosBasicos;
    }

    public void setTieneServiciosBasicos(Boolean tieneServiciosBasicos) {
        this.tieneServiciosBasicos = tieneServiciosBasicos;
    }

    public String getMaterialPrincipal() {
        return materialPrincipal;
    }

    public void setMaterialPrincipal(String materialPrincipal) {
        this.materialPrincipal = materialPrincipal;
    }

    public Integer getNumHabitantes() {
        return numHabitantes;
    }

    public void setNumHabitantes(Integer numHabitantes) {
        this.numHabitantes = numHabitantes;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<ActividadEconomica> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadEconomica> actividades) {
        this.actividades = actividades;
    }

    public List<Habitante> getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(List<Habitante> habitantes) {
        this.habitantes = habitantes;
    }
}
