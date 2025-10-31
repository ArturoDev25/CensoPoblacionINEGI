# 🧮 Censo de Población INEGI

![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue?logo=postgresql)
![Thymeleaf](https://img.shields.io/badge/View-Thymeleaf-green?logo=thymeleaf)
![License](https://img.shields.io/badge/License-Educational-lightgrey)

---

## 📖 Descripción del proyecto

**Censo de Población INEGI** es una aplicación web desarrollada con **Spring Boot**, diseñada para simular un sistema de registro y gestión poblacional inspirado en los censos oficiales del **INEGI**.  
El sistema permite administrar **usuarios, viviendas y localidades**, aplicando los principios del patrón **MVC (Modelo–Vista–Controlador)** y conectándose a una base de datos **PostgreSQL** alojada en **Supabase**.

El proyecto forma parte del **Proyecto Parcial 2 – Arquitectura de Software**, implementando conceptos de modelado, diseño en capas y patrones arquitectónicos modernos.

---
## 🚀 Tecnologías utilizadas

| Tecnología | Descripción |
|-------------|--------------|
| **Java 17** | Lenguaje principal del proyecto |
| **Spring Boot 3.x** | Framework para el backend |
| **Spring Data JPA** | Mapeo objeto-relacional con la base de datos |
| **Thymeleaf** | Motor de plantillas para vistas dinámicas |
| **Bootstrap 5** | Framework CSS para diseño responsivo |
| **PostgreSQL (Supabase)** | Base de datos en la nube |
| **Maven** | Gestión de dependencias y empaquetado |

---

## 🧩 Arquitectura del proyecto

El proyecto sigue la arquitectura **MVC (Modelo–Vista–Controlador)**, donde:

- **Model** → Contiene las entidades y la lógica de acceso a datos.  
- **View** → Son las plantillas HTML dinámicas con **Thymeleaf**.  
- **Controller** → Gestiona las peticiones HTTP y coordina la comunicación entre la vista y el modelo.

---


## ⚙️ Configuración del entorno

### 🔧 1. Clonar el repositorio
```bash
git clone https://github.com/ArturoDev25/CensoDePoblacionINEGI-Update1.git
cd CensoDePoblacionINEGI-Update1
```
2. Instalar Maven en CMD windows en cd CensoPoblacionINEGI\censo
```bash 
mvnw.cmd clean install
```
3. Descarga las dependencias en vs
  ```
Extension Pack for Java
Spring Boot Extension Pack
Maven for Java
```
