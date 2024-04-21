package com.inmnova.gestion_inmobiliaria.alquileres.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class alquileresController {

    @GetMapping(path = "/alquileres")
    public String mostrarAlquileres(Model model) {
        return "alquileres/index";
    }
}