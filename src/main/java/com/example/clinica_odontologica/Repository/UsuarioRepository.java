package com.example.clinica_odontologica.Repository;



import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clinica_odontologica.Entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);

}
