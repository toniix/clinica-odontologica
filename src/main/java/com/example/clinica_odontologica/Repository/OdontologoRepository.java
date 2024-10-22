package com.example.clinica_odontologica.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clinica_odontologica.Entity.Odontologo;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    
    Optional<Odontologo> findByNumeroMatricula(Integer matricula); ;
}
