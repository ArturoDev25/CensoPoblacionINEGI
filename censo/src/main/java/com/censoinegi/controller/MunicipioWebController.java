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

import com.censoinegi.model.Municipio;
import com.censoinegi.service.LoginService;
import com.censoinegi.service.MunicipioService;

@Controller
@RequestMapping("/municipios")
public class MunicipioWebController {

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String listar(Model model) {
        if (!loginService.haySesionActiva()) {// Verifica si hay una sesión activa
            return "redirect:/login";
        }

        model.addAttribute("municipios", municipioService.findAll());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "municipios/municipios";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        if (!loginService.haySesionActiva()) {// Verifica si hay una sesión activa
            return "redirect:/login";
        }

        model.addAttribute("municipio", new Municipio());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "municipios/municipios_form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        return municipioService.findById(id)
                .map(municipio -> {
                    model.addAttribute("municipio", municipio);
                    model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
                    return "municipios/municipios_form";
                })
                .orElseGet(() -> {
                    redirect.addFlashAttribute("error", "Municipio no encontrado");
                    return "redirect:/municipios";
                });
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Municipio municipio, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            boolean esNuevo = (municipio.getId() == null);
            municipioService.save(municipio);
            
            // Mensaje diferente según si es creación o edición
            if (esNuevo) {
                redirect.addFlashAttribute("success", "Municipio guardado exitosamente");
            } else {
                redirect.addFlashAttribute("success", "Municipio editado exitosamente");
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Error al guardar municipio: " + e.getMessage());
        }

        return "redirect:/municipios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            municipioService.deleteById(id);
            redirect.addFlashAttribute("success", "Municipio eliminado exitosamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }

        return "redirect:/municipios";
    }
}