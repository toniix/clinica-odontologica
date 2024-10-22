package com.example.clinica_odontologica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_odontologica.Dto.TurnoDTO;
import com.example.clinica_odontologica.Entity.Turno;
import com.example.clinica_odontologica.Exception.ResourceNotFoundException;
import com.example.clinica_odontologica.Repository.TurnoRepository;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public TurnoDTO registrarTurno(Turno turno){
        Turno turnoGuardado= turnoRepository.save(turno);
        return turnoATurnoDTO(turnoGuardado);
    }

    public TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO dto= new TurnoDTO();
        dto.setId(turno.getId());
        dto.setPacienteId(turno.getPaciente().getId());
        dto.setOdontologoId(turno.getOdontologo().getId());
        dto.setFecha(turno.getFecha());
        return dto;

    }

    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            return turnoBuscado;
        } else
            throw new ResourceNotFoundException("Turno no encontrado");
    }

    public Turno actualizarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    public void borrarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

}
