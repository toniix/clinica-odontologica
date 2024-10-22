package com.example.clinica_odontologica.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.clinica_odontologica.Entity.Usuario;
import com.example.clinica_odontologica.Entity.UsuarioRole;
import com.example.clinica_odontologica.Repository.UsuarioRepository;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passCifrado= bCryptPasswordEncoder.encode("admin");
        Usuario usuario= new Usuario("Anthony", "Devton", passCifrado, "devton@dev.com", UsuarioRole.ROLE_ADMIN);
        System.out.println("pass: "+passCifrado);
        usuarioRepository.save(usuario);

    }
}
