package com.example.clinica_odontologica.service;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.clinica_odontologica.Entity.Domicilio;
import com.example.clinica_odontologica.Entity.Paciente;
import com.example.clinica_odontologica.Exception.BadRequestException;
import com.example.clinica_odontologica.Exception.ResourceNotFoundException;
import com.example.clinica_odontologica.Service.PacienteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente() throws BadRequestException{
        Paciente paciente= new Paciente("Juan", "Perez", "12345678", LocalDate.of(2024, 8, 12), new Domicilio("Calle 1",12,"La Rioja","La Rioja"));
        Paciente pacienteGuardado= pacienteService.guardarPaciente(paciente);
        assertEquals(1L,pacienteGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarPaciente() throws ResourceNotFoundException{
        Long id=1L;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorId(id);
        assertNotNull(pacienteBuscado.get());
    }
    @Test
    @Order(3)
    public void actualizarPaciente() throws ResourceNotFoundException, BadRequestException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorId(1L);
        if(pacienteBuscado.isPresent()){
            pacienteBuscado.get().setApellido("Perez");
        }
        pacienteService.actualizarPaciente(pacienteBuscado.get());
        assertEquals("Perez",pacienteBuscado.get().getApellido());
    }
    @Test
    @Order(4)
    public void listarTodos(){
        List<Paciente> pacientes= pacienteService.listarPacientes();
        assertEquals(1,pacientes.size());
    }
    // @Test
    // @Order(5)
    // public void eliminaPaciente() throws ResourceNotFoundException{
    //     pacienteService.borrarPaciente(1L);
    //     Optional<Paciente> pacienteEliminado= pacienteService.buscarPacientePorId(1L);
    //     assertFalse(pacienteEliminado.isPresent());
    // }
}
