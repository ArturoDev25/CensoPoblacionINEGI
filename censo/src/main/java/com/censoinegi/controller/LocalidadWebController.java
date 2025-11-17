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

import com.censoinegi.model.Localidad;
import com.censoinegi.service.LocalidadService;
import com.censoinegi.service.LoginService;
import com.censoinegi.service.MunicipioService;

@Controller
@RequestMapping("/localidades")
public class LocalidadWebController {

    @Autowired
    private LocalidadService localidadService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String listar(Model model) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        model.addAttribute("localidades", localidadService.findAll());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "localidades/localidades";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        model.addAttribute("localidad", new Localidad());
        model.addAttribute("municipios", municipioService.findAll());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "localidades/localidades_form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        return localidadService.findById(id)
                .map(localidad -> {
                    model.addAttribute("localidad", localidad);
                    model.addAttribute("municipios", municipioService.findAll());
                    model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
                    return "localidades/localidades_form";
                })
                .orElseGet(() -> {
                    redirect.addFlashAttribute("error", "Localidad no encontrada");
                    return "redirect:/localidades";
                });
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Localidad localidad, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            System.out.println("GUARDANDO LOCALIDAD -> " + localidad.getNombre()); // DEBUG
            localidadService.save(localidad);
            
            // Mensaje diferenciado según si es creación o edición
            if (localidad.getId() == null) {
                redirect.addFlashAttribute("success", "Localidad creada exitosamente");
            } else {
                redirect.addFlashAttribute("success", "Localidad editada exitosamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Error al guardar localidad: " + e.getMessage());
        }

        return "redirect:/localidades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            localidadService.deleteById(id);
            redirect.addFlashAttribute("success", "Localidad eliminada exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }

        return "redirect:/localidades";
    }
}