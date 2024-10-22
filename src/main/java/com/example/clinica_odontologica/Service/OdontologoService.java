package com.example.clinica_odontologica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_odontologica.Entity.Odontologo;
import com.example.clinica_odontologica.Exception.BadRequestException;
import com.example.clinica_odontologica.Exception.ResourceNotFoundException;
import com.example.clinica_odontologica.Repository.OdontologoRepository;
// import com.example.clinica_odontologica.Repository.TurnoRepository;

@Service
public class OdontologoService {
    
    @Autowired
    private OdontologoRepository odontologoRepository;

    // @Autowired
    // private TurnoRepository turnoRepository;


    public Odontologo guardarOdontologo(Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findByNumeroMatricula(odontologo.getNumeroMatricula());
        if (odontologoBuscado.isPresent()) {
            throw new BadRequestException("El numero de matricula ya existe. Registre otro");
        } else{
            return odontologoRepository.save(odontologo);
        }
    }

    public Optional<Odontologo> buscarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoRepository.findById(id);
        if(odontologoBuscado.isPresent()){
            return odontologoBuscado;
        } else {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
       
    }   

    public List<Odontologo> listarOdontologos() {
        return odontologoRepository.findAll();
    }

    public void actualizarOdontologo(Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(odontologo.getId());
        if(odontologoBuscado.isPresent()){

            odontologoBuscado.get().setNumeroMatricula(odontologo.getNumeroMatricula());
            odontologoBuscado.get().setNombre(odontologo.getNombre());
            odontologoBuscado.get().setApellido(odontologo.getApellido());

            odontologoRepository.save(odontologoBuscado.get());
        }else{
            throw new BadRequestException("Odontologo no encontrado");
        }
    }


    public void borrarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }


    public Optional<Odontologo> buscarPorMatricula(Integer matricula) {
        return odontologoRepository.findByNumeroMatricula(matricula);
    }


    // public Long contarPacientesPorOdontologo(Long odontologoId) {
    //     return turnoRepository.countDistinctPacientesByOdontologoId(odontologoId);
    // }
}
