package com.example.clinica_odontologica.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
// import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

// import com.example.clinica_odontologica.Dto.TurnoDTO;
import com.example.clinica_odontologica.Entity.Odontologo;
import com.example.clinica_odontologica.Entity.Paciente;
import com.example.clinica_odontologica.Entity.Turno;
import com.example.clinica_odontologica.Service.OdontologoService;
import com.example.clinica_odontologica.Service.PacienteService;
import com.example.clinica_odontologica.Service.TurnoService;

@Controller
@RequestMapping("/turno")
public class TurnoController {
    
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    

    @GetMapping("/registrar")
    public String nuevoTurno(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        model.addAttribute("userName", userName);

        List<Paciente> pacientes = pacienteService.listarPacientes();
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
    
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("odontologos", odontologos);

        return "post_turno";
    }

    @PostMapping("/registrar")
    public String registrarTurno2(@ModelAttribute Turno turno, Model model) {
        try {
        
            if (turno.getFecha() == null) {
                model.addAttribute("errorMessage", "La fecha del turno es obligatoria");
                return "post_turno";  // Redirige de vuelta al formulario si hay error
            }

            Optional<Paciente> pacienteOpt = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
            if (!pacienteOpt.isPresent()) {
                model.addAttribute("errorMessage", "Paciente no encontrado");
                return "post_turno";  // Redirige de vuelta al formulario si hay error
            }

            Optional<Odontologo> odontologoOpt = odontologoService.buscarOdontologo(turno.getOdontologo().getId());
            if (!odontologoOpt.isPresent()) {
                model.addAttribute("errorMessage", "Odontólogo no encontrado");
                return "post_turno";  // Redirige de vuelta al formulario si hay error
            }

            Paciente paciente = pacienteOpt.get();
            Odontologo odontologo = odontologoOpt.get();

            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);

            turnoService.registrarTurno(turno);

            return "redirect:/turno/registrar";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocurrió un error al registrar el turno: " + e.getMessage());
            return "post_turno";  // Redirige de vuelta al formulario si hay error
        }
    }

    @GetMapping("")
    public String listarTodos(Model model){
        List<Turno> turnos = turnoService.listarTurnos();
        model.addAttribute("turnos", turnos);
        return "turnos";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam Long id, Model model){
        try {
            Turno turno = turnoService.buscarTurnoPorId(id).get();
            model.addAttribute("turno", turno);
            return "turnos";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "turnos";
        }
    }
}
