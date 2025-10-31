package com.censoinegi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.censoinegi.model.Vivienda;
import com.censoinegi.service.MunicipioService;
import com.censoinegi.service.TipoViviendaService;
import com.censoinegi.service.ViviendaService;

@Controller
@RequestMapping("/viviendas")
public class ViviendaWebController {

    @Autowired
    private ViviendaService viviendaService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private TipoViviendaService tipoViviendaService;

    // Mostrar listado
    @GetMapping
    public String listar(Model model) {
        List<Vivienda> lista = viviendaService.findAll();
        model.addAttribute("viviendas", lista);
        return "viviendas/viviendas";
    }

    // Mostrar formulario para nueva vivienda
    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("vivienda", new Vivienda());
        model.addAttribute("municipios", municipioService.findAll());
        model.addAttribute("tipos", tipoViviendaService.findAll());
        return "viviendas/vivienda_form";
    }

    // Guardar vivienda
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Vivienda vivienda) {
        viviendaService.save(vivienda);
        return "redirect:/viviendas";
    }

    // Editar vivienda
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, Model model) {
        Vivienda vivienda = viviendaService.findById(id).orElse(null);
        model.addAttribute("vivienda", vivienda);
        model.addAttribute("municipios", municipioService.findAll());
        model.addAttribute("tipos", tipoViviendaService.findAll());
        return "vivienda_form";
    }

    // Eliminar vivienda
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable UUID id) {
        viviendaService.deleteById(id);
        return "redirect:/viviendas";
    }
}
