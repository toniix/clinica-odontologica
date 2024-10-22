package com.example.clinica_odontologica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.clinica_odontologica.Dto.TurnoDTO;
import com.example.clinica_odontologica.Entity.Domicilio;
import com.example.clinica_odontologica.Entity.Odontologo;
import com.example.clinica_odontologica.Entity.Paciente;
import com.example.clinica_odontologica.Entity.Turno;
import com.example.clinica_odontologica.Exception.BadRequestException;
import com.example.clinica_odontologica.Service.OdontologoService;
import com.example.clinica_odontologica.Service.PacienteService;
import com.example.clinica_odontologica.Service.TurnoService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;;

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void registrarTurno() throws BadRequestException {
        Paciente paciente= new Paciente("Juan", "Perez", "1234528", LocalDate.of(2024, 8, 12), new Domicilio("Calle 1",12,"La Rioja","La Rioja"));
        Odontologo odontologo = new Odontologo(1233, "Anthony", "War" );
        odontologoService.guardarOdontologo(odontologo);
        pacienteService.guardarPaciente(paciente);

        Turno turno = new Turno(paciente, odontologo, LocalDate.of(2024, 8, 12));
        TurnoDTO turnoGuardado = turnoService.registrarTurno(turno);
        assertEquals(1L, turnoGuardado.getId());


    }
}
