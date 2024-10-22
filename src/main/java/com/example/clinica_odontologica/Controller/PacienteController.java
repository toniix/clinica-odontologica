package com.example.clinica_odontologica.Controller;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.clinica_odontologica.Entity.Paciente;
import com.example.clinica_odontologica.Service.PacienteService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/paciente")
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("")
    public String pacienteHome() {
        return "pacientes";
    }

    @GetMapping("/nuevo")
    public String nuevoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "post_pacientes";
    }
    
     @PostMapping("/registrar")
    public String registrarPaciente(@ModelAttribute("paciente") @Valid Paciente paciente, BindingResult result, Model model){
        try {
            if (result.hasErrors()) {
                return "post_pacientes";
                }
            pacienteService.guardarPaciente(paciente);
            return "redirect:/paciente";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "post_pacientes";
        }
    }

    @GetMapping("/listar")
    public String listarPacientes(Model model){
        List<Paciente> pacientes = pacienteService.listarPacientes();
        model.addAttribute("pacientes", pacientes);
        return "get_pacientes";
    }

    @GetMapping("/{pacienteId}/actualizar")
    public String formActualizarPaciente(@PathVariable Long pacienteId, Model model) {
        try {
            Paciente paciente = pacienteService.buscarPacientePorId(pacienteId).get();
            model.addAttribute("paciente", paciente);
            return "update_pacientes";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "update_pacientes";
        }
    }
    @PostMapping("/{pacienteId}/actualizar")
    public String actualizarPaciente(@PathVariable("pacienteId") Long pacienteId, @ModelAttribute("paciente") @Valid Paciente paciente, BindingResult result, Model model) {
        try {
             if (result.hasErrors()) {
                return "post_pacientes";
                }
            pacienteService.actualizarPaciente(paciente);
            return "redirect:/paciente/listar";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "update_pacientes";
        }
    }


    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam Long id, Model model){
        try {
            Paciente paciente = pacienteService.buscarPacientePorId(id).get();
            model.addAttribute("paciente", paciente);
            return "pacientes";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "pacientes";
        } 
    }

    @PostMapping("/{pacienteId}/eliminar")
    public String eliminarPaciente(@PathVariable("pacienteId") Long pacienteId, Model model) {
        try {
            pacienteService.borrarPaciente(pacienteId);
            return "redirect:/paciente/listar";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "pacientes";
        }
    }
    
}

