package com.inmnova.gestion_inmobiliaria.Usuarios.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class loginController {

    @GetMapping("path")
    public String getLogin(Model model) {
        return new String();
    }
}
