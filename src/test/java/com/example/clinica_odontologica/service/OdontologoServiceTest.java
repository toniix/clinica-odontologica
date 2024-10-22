package com.example.clinica_odontologica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.clinica_odontologica.Entity.Odontologo;
import com.example.clinica_odontologica.Exception.BadRequestException;
import com.example.clinica_odontologica.Exception.ResourceNotFoundException;
import com.example.clinica_odontologica.Service.OdontologoService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    
    @Test
    @Order(1)
    public void guardarOdontologo() throws BadRequestException {
        Odontologo odontologo = new Odontologo(1233, "Anthony", "War" );
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(1L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologo() throws ResourceNotFoundException {
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo() throws ResourceNotFoundException, BadRequestException {  
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(1L);
        if (odontologoBuscado.isPresent()) {
            odontologoBuscado.get().setApellido("Domingues");
        }
        odontologoService.actualizarOdontologo(odontologoBuscado.get());
        assertEquals("Domingues", odontologoBuscado.get().getApellido());
    }

    @Test
    @Order(4)
    public void borrarOdontologo() throws ResourceNotFoundException {
        odontologoService.borrarOdontologo(1L);
        assertEquals(0, odontologoService.listarOdontologos().size());
    }
}
