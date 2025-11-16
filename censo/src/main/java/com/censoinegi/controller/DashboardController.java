package com.censoinegi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.censoinegi.dto.LocalidadEstadisticas;
import com.censoinegi.dto.MunicipioEstadisticas;
import com.censoinegi.service.ActividadEconomicaService;
import com.censoinegi.service.HabitanteService;
import com.censoinegi.service.LocalidadService;
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
    private LocalidadService localidadService;

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
        model.addAttribute("totalLocalidades", localidadService.contarLocalidades());

        return "menu/dashboard";
    }

    //Endpoint para obtener datos de municipios y localidades para gráficos

    @GetMapping("/api/dashboard/municipios")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> obtenerDatosMunicipios() {
        List<MunicipioEstadisticas> estadisticas = municipioService.obtenerEstadisticasPorMunicipio();

        // Top 10 municipios con más habitantes
        List<MunicipioEstadisticas> top10 = estadisticas.stream()
                .limit(10)
                .collect(Collectors.toList());

        List<String> labels = top10.stream()
                .map(MunicipioEstadisticas::getNombreMunicipio)
                .collect(Collectors.toList());

        List<Long> habitantes = top10.stream()
                .map(MunicipioEstadisticas::getTotalHabitantes)
                .collect(Collectors.toList());

        List<Long> viviendas = top10.stream()
                .map(MunicipioEstadisticas::getTotalViviendas)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("labels", labels);
        response.put("habitantes", habitantes);
        response.put("viviendas", viviendas);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/dashboard/localidades")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> obtenerDatosLocalidades() {
        List<LocalidadEstadisticas> estadisticas = localidadService.obtenerEstadisticasPorLocalidad();

        // Top 10 localidades con más habitantes
        List<LocalidadEstadisticas> top10 = estadisticas.stream()
                .limit(10)
                .collect(Collectors.toList());

        List<String> labels = top10.stream()
                .map(loc -> loc.getNombreLocalidad() + " (" + loc.getNombreMunicipio() + ")")
                .collect(Collectors.toList());

        List<Long> habitantes = top10.stream()
                .map(LocalidadEstadisticas::getTotalHabitantes)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("labels", labels);
        response.put("habitantes", habitantes);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/dashboard/tabla-municipios")
    @ResponseBody
    public ResponseEntity<List<MunicipioEstadisticas>> obtenerTablaMunicipios() {
        return ResponseEntity.ok(municipioService.obtenerEstadisticasPorMunicipio());
    }
}