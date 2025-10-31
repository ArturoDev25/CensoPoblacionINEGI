package com.censoinegi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.censoinegi.model.Usuario;
import com.censoinegi.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLoginDirecto() {
        return "login";
    }

    // Procesar el login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam("correo") String correo,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        Usuario usuario = usuarioService.autenticar(correo, password);

        if (usuario != null) {
            return "redirect:/menu";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

    // Cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
