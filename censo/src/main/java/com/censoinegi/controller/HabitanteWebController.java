package com.censoinegi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.censoinegi.model.Habitante;
import com.censoinegi.service.ActividadEconomicaService;
import com.censoinegi.service.HabitanteService;
import com.censoinegi.service.LoginService;
import com.censoinegi.service.ViviendaService;

@Controller
@RequestMapping("/habitantes")
public class HabitanteWebController {

    @Autowired
    private HabitanteService habitanteService;

    @Autowired
    private ViviendaService viviendaService;

    @Autowired
    private ActividadEconomicaService actividadEconomicaService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String listar(Model model) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        model.addAttribute("habitantes", habitanteService.findAll());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "habitantes/habitantes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        model.addAttribute("habitante", new Habitante());
        model.addAttribute("viviendas", viviendaService.findAll());
        model.addAttribute("actividades", actividadEconomicaService.findAll());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "habitantes/habitantes_form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        return habitanteService.findById(id)
                .map(habitante -> {
                    model.addAttribute("habitante", habitante);
                    model.addAttribute("viviendas", viviendaService.findAll());
                    model.addAttribute("actividades", actividadEconomicaService.findAll());
                    model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
                    return "habitantes/habitantes_form";
                })
                .orElseGet(() -> {
                    redirect.addFlashAttribute("error", "Habitante no encontrado");
                    return "redirect:/habitantes";
                });
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("habitante") Habitante habitante,
            RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            System.out.println("GUARDANDO HABITANTE -> " + habitante.getNombre()); // DEBUG
            System.out.println("ACTIVIDAD ECONÓMICA -> " + 
                (habitante.getActividadEconomica() != null ? 
                    habitante.getActividadEconomica().getNombre() : "Sin actividad")); // DEBUG
            
            habitanteService.save(habitante);
            redirect.addFlashAttribute("success", "Habitante guardado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Error al guardar habitante: " + e.getMessage());
        }

        return "redirect:/habitantes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            habitanteService.deleteById(id);
            redirect.addFlashAttribute("success", "Habitante eliminado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }

        return "redirect:/habitantes";
    }
}

