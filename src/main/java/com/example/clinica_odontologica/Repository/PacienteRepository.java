package com.example.clinica_odontologica.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clinica_odontologica.Entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    Optional<Paciente> findByDni(String dni);
}
