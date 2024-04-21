package com.inmnova.gestion_inmobiliaria.Usuarios.Controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inmnova.gestion_inmobiliaria.Usuarios.Model.User;
import com.inmnova.gestion_inmobiliaria.Usuarios.Repositories.UsuarioRepository;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
@RequestMapping(path = "/users")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String getAllUsers(Model model) {        
        model.addAttribute("usuarios", usuarioRepository.getAllUsers());
        return "usuarios/index";
    }
    
    @GetMapping("/getById/{id}")
    public String getUsersById(Model model, @PathVariable int id) {
        model.addAttribute("usuario", usuarioRepository.getUserById(id));
        return "usuarios/detalles";
    }

    @GetMapping("/update/{id}")
    public String getUpdateView(Model model, @PathVariable int id) {
        model.addAttribute("usuario", usuarioRepository.getUserById(id));
        return "usuarios/modificar";
    }    

    @PutMapping("/{id}")
    public String updateUser(Model model, @PathVariable int id, @ModelAttribute User user) throws SQLException  {
        usuarioRepository.updateUser(id, user);
        model.addAttribute("usuarios", usuarioRepository.getAllUsers());
        return "usuarios/index";
    }
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) throws SQLException {
        usuarioRepository.deleteUser(id);
    }
}