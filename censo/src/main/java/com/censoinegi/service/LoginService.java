package com.censoinegi.service;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.censoinegi.model.Usuario;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginService {
    
    private Usuario usuarioActivo;

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void iniciarSesion(Usuario usuario) {
        this.usuarioActivo = usuario;
    }

    public void cerrarSesion() {
        this.usuarioActivo = null;
    }

    public boolean haySesionActiva() {
        return usuarioActivo != null;
    }
    
}