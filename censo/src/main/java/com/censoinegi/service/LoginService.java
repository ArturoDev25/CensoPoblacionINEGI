package com.censoinegi.service;

import com.censoinegi.model.Usuario;

/**
 * Servicio Singleton que mantiene el estado del usuario activo.
 * Aplica patrón Singleton y desacopla la logica de sesion de los controladores.
 */
public class LoginService {

    private static Usuario usuarioActivo;

    private LoginService() {
        // Constructor privado para evitar instanciacion 
    }

    public static Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public static void iniciarSesion(Usuario usuario) {
        usuarioActivo = usuario;
    }

    public static void cerrarSesion() {
        usuarioActivo = null;
    }

    public static boolean haySesionActiva() {
        return usuarioActivo != null;
    }
}





