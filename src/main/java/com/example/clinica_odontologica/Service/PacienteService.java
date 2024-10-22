package com.example.clinica_odontologica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.clinica_odontologica.Entity.Domicilio;
import com.example.clinica_odontologica.Entity.Paciente;
import com.example.clinica_odontologica.Exception.BadRequestException;
import com.example.clinica_odontologica.Exception.ResourceNotFoundException;
import com.example.clinica_odontologica.Repository.PacienteRepository;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;


    public Paciente guardarPaciente(Paciente paciente) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findByDni(paciente.getDni());
        if (pacienteBuscado.isPresent()) {
            throw new BadRequestException("El DNI ya existe. Registre otro");
        } else {
            Domicilio domicilio = paciente.getDomicilio();
            domicilio.setPaciente(paciente);
            return pacienteRepository.save(paciente);
        }
        
    }

    public Optional<Paciente> buscarPacientePorId(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteRepository.findById(id);
        if(pacienteBuscado.isPresent()){
            return pacienteBuscado;
        }else
            throw new ResourceNotFoundException("paciente no encontrado");
    }
        
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }        
     public void actualizarPaciente(Paciente paciente) throws BadRequestException {
        Optional<Paciente> pacienteBuscado= pacienteRepository.findById(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteBuscado.get().setDni(paciente.getDni());
            pacienteBuscado.get().setNombre(paciente.getNombre());
            pacienteBuscado.get().setApellido(paciente.getApellido());
            pacienteBuscado.get().setDomicilio(paciente.getDomicilio());
            pacienteRepository.save(pacienteBuscado.get());
        }else{
            throw new BadRequestException("paciente no encontrado");
        }
    }
    public void borrarPaciente(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteRepository.findById(id);
        if(pacienteBuscado.isPresent()){
            pacienteRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("paciente no encontrado");
        }
        }

    public Optional<Paciente> buscarPorDni(String dni) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findByDni(dni);
        if (pacienteBuscado.isPresent()) {
            return pacienteBuscado;
        } else {
            throw new ResourceNotFoundException("paciente no encontrado");
        }
        
    }

    


    
}
