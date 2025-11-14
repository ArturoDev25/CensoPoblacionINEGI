package com.censoinegi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.censoinegi.model.Usuario;
import com.censoinegi.service.UsuarioService;
import com.censoinegi.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private LoginService loginService;  // ✅ AGREGADO

    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        if (logout != null) {
            model.addAttribute("logoutMsg", "Sesión cerrada correctamente.");
        }
        if (loginService.haySesionActiva()) {  // ✅ CAMBIADO
            return "redirect:/dashboard";
        }
        return "login";
    }

    
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                @RequestParam String password,
                                Model model) {

        Usuario usuario = usuarioService.autenticar(correo, password);

        if (usuario != null) {
            loginService.iniciarSesion(usuario);  // ✅ CAMBIADO
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }


    @GetMapping("/logout")
    public String cerrarSesion() {
        loginService.cerrarSesion();  // ✅ CAMBIADO
        return "redirect:/login?logout=true";
    }
    
}