package com.censoinegi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.censoinegi.service.ActividadEconomicaService;
import com.censoinegi.service.HabitanteService;
import com.censoinegi.service.LoginService;
import com.censoinegi.service.MunicipioService;
import com.censoinegi.service.ViviendaService;

@Controller
public class DashboardController {

    @Autowired
    private ViviendaService viviendaService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private HabitanteService habitanteService;

    @Autowired
    private ActividadEconomicaService actividadService;

    @Autowired
    private LoginService loginService;

    @GetMapping({ "/", "/dashboard" })
    public String mostrarDashboard(Model model) {

        if (!loginService.haySesionActiva()) {
            return "redirect:/login";
        }

        model.addAttribute("usuarioActivo", loginService.getUsuarioActivo().getNombre());
        model.addAttribute("totalMunicipios", municipioService.contarMunicipios());
        model.addAttribute("totalViviendas", viviendaService.contarViviendas());
        model.addAttribute("totalHabitantes", habitanteService.contarHabitantes());
        model.addAttribute("totalActividades", actividadService.contarActividades());

        return "menu/dashboard";
    }

}
