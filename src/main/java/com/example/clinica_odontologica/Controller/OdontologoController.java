package com.example.clinica_odontologica.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.clinica_odontologica.Entity.Odontologo;
import com.example.clinica_odontologica.Service.OdontologoService;

import jakarta.validation.Valid;

// @RestController
@Controller
@RequestMapping("odontologo")
public class OdontologoController {
    

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("")
    public String odontologoHome() {
        return "odontologos";
    }

    @GetMapping("/nuevo")
    public String nuevoOdontologo(Model model) {
        model.addAttribute("odontologo", new Odontologo());
        return "post_odontologos";
    }

    @PostMapping("/registrar")
    public String registrarODontologo(@ModelAttribute("odontologo") @Valid Odontologo odontologo, BindingResult result, Model model){
        try {
            if (result.hasErrors()) {
                return "post_odontologos";
                }
            odontologoService.guardarOdontologo(odontologo);
            return "redirect:/odontologo";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "post_odontologos";
        }
    }

    @GetMapping("/listar")
    public String listarOdontologos(Model model){
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        model.addAttribute("odontologos", odontologos);
        return "get_odontologos";
    }

    @GetMapping("/{odontologoId}/actualizar")
    public String formActualizarOdontologo(@PathVariable Long odontologoId, Model model) {
        try {
            Odontologo odontologo = odontologoService.buscarOdontologo(odontologoId).get();
            model.addAttribute("odontologo", odontologo);
            return "update_odontologo";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "update_odontologo";
        }
    }

    @PostMapping("/{odontologoId}/actualizar")
    public String actualizarOdontologo(@PathVariable("odontologoId") Long odontologoId, @ModelAttribute("odontologo") Odontologo odontologo, Model model) {
       try {
            odontologoService.actualizarOdontologo(odontologo);
            return "redirect:/odontologo/listar";
       } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "update_odontologo";
       }
        
    }


    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        try {
            Odontologo odontologo = odontologoService.buscarOdontologo(id).get();
            model.addAttribute("odontologo", odontologo);
            return "odontologos";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "odontologos";
        }
    }


    @PostMapping("/{odontologoId}/eliminar")
    public String borrarOdontologo(@PathVariable("odontologoId") Long id) {
        odontologoService.borrarOdontologo(id);
        return "redirect:/odontologo/listar";
    }

    // @GetMapping("/{odontologoId}/pacientes/count")
    // public ResponseEntity<Long> contarPacientes(@PathVariable Long odontologoId) {
    //     Long numeroDePacientes = odontologoService.contarPacientesPorOdontologo(odontologoId);
    //     return ResponseEntity.ok(numeroDePacientes);
    // }

    
}
