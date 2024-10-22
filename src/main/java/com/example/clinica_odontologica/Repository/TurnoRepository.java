package com.example.clinica_odontologica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.clinica_odontologica.Entity.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    
     // Contar pacientes únicos por odontólogo
    @Query("SELECT COUNT(DISTINCT t.paciente) FROM Turno t WHERE t.odontologo.id = :odontologoId")
    Long countDistinctPacientesByOdontologoId(@Param("odontologoId") Long odontologoId);

}
