package com.example.clinica_odontologica.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.clinica_odontologica.Dto.UsuarioDTO;
import com.example.clinica_odontologica.Exception.EmailAlreadyExistsException;
import com.example.clinica_odontologica.Exception.PasswordMismatchException;
import com.example.clinica_odontologica.Service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/register")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "register";  
    }

    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        try {
            usuarioService.guardarUsuario(usuarioDTO);
            return "redirect:/login";
        } catch (PasswordMismatchException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register"; // Nombre de la vista de registro
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("emailErrorMessage", e.getMessage());
            return "register"; // Nombre de la vista de registro
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/post_odontologos")
    public String afterLogin() {
        return "post_odontologos";
    }

}
