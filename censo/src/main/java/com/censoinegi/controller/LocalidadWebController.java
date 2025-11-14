package com.censoinegi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.censoinegi.model.Localidad;
import com.censoinegi.service.LocalidadService;
import com.censoinegi.service.MunicipioService;
import com.censoinegi.service.LoginService;

/**
 * Controlador WEB para gestión de localidades (vistas HTML)
 */
@Controller
@RequestMapping("/localidades")
public class LocalidadWebController {

    @Autowired
    private LocalidadService localidadService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private LoginService loginService;

    // Listar localidades
    @GetMapping
    public String listar(Model model) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        model.addAttribute("localidades", localidadService.findAll());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "localidades/localidades";
    }

    // Formulario nueva localidad
    @GetMapping("/nueva")
    public String nueva(Model model) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        model.addAttribute("localidad", new Localidad());
        model.addAttribute("municipios", municipioService.findAll());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "localidades/localidad_form";
    }

    // Formulario editar localidad
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, Model model, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        return localidadService.findById(id)
            .map(localidad -> {
                model.addAttribute("localidad", localidad);
                model.addAttribute("municipios", municipioService.findAll());
                model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
                return "localidades/localidad_form";
            })
            .orElseGet(() -> {
                redirect.addFlashAttribute("error", "Localidad no encontrada");
                return "redirect:/localidades";
            });
    }

    // Guardar localidad
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Localidad localidad, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            localidadService.save(localidad);
            redirect.addFlashAttribute("success", "Localidad guardada exitosamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Error: " + e.getMessage());
        }

        return "redirect:/localidades";
    }

    // Eliminar localidad
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable UUID id, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            localidadService.deleteById(id);
            redirect.addFlashAttribute("success", "Localidad eliminada exitosamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }

        return "redirect:/localidades";
    }
}