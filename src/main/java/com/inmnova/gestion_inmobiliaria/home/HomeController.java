package com.inmnova.gestion_inmobiliaria.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index.html"})
    public String Inicio() {
        return "index";
    }
}