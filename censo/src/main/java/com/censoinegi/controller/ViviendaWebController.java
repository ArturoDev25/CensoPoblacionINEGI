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

import com.censoinegi.model.Vivienda;
import com.censoinegi.service.LoginService;
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

    @Autowired
    private LoginService loginService;

    // Listar viviendas
    @GetMapping
    public String listar(Model model) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        model.addAttribute("viviendas", viviendaService.findAll());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "viviendas/viviendas";
    }

    // Formulario nueva vivienda
    @GetMapping("/nueva")
    public String nueva(Model model) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        model.addAttribute("vivienda", new Vivienda());
        model.addAttribute("municipios", municipioService.findAll());
        model.addAttribute("tipos", tipoViviendaService.findAll());
        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        return "viviendas/vivienda_form";
    }

    // 👉 CAMBIO: id ahora es String para coincidir con Vivienda.id
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        return viviendaService.findById(id)
            .map(vivienda -> {
                model.addAttribute("vivienda", vivienda);
                model.addAttribute("municipios", municipioService.findAll());
                model.addAttribute("tipos", tipoViviendaService.findAll());
                model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
                return "viviendas/vivienda_form";
            })
            .orElseGet(() -> {
                redirect.addFlashAttribute("error", "Vivienda no encontrada");
                return "redirect:/viviendas";
            });
    }

    // Guardar vivienda (nueva o editada)
    // 👉 CAMBIO: nombre correcto del RedirectAttributes y debug
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("vivienda") Vivienda vivienda,
                          RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            System.out.println("GUARDANDO VIVIENDA -> " + vivienda.getDireccion()); // DEBUG
            viviendaService.save(vivienda);
            redirect.addFlashAttribute("success", "Vivienda guardada exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Error al guardar vivienda: " + e.getMessage());
        }

        return "redirect:/viviendas";
    }

    // 👉 CAMBIO: id también como String
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirect) {
        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        try {
            viviendaService.deleteById(id);
            redirect.addFlashAttribute("success", "Vivienda eliminada");
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", " Error al eliminar");
        }

        return "redirect:/viviendas";
    }
}
