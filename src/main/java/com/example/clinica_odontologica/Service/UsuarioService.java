package com.example.clinica_odontologica.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.clinica_odontologica.Dto.UsuarioDTO;
import com.example.clinica_odontologica.Entity.Usuario;
import com.example.clinica_odontologica.Entity.UsuarioRole;
import com.example.clinica_odontologica.Exception.EmailAlreadyExistsException;
import com.example.clinica_odontologica.Exception.PasswordMismatchException;
import com.example.clinica_odontologica.Repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
   private UsuarioRepository usuarioRepository;

   @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
   
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Usuario> usuarioBuscado= usuarioRepository.findByEmail(username);
       if(usuarioBuscado.isPresent()){
           return usuarioBuscado.get();
       }
       else{
          throw new UsernameNotFoundException("no existe el usuario :"+username) ;
       }
   }

   public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws EmailAlreadyExistsException, PasswordMismatchException {

       Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuarioDTO.getEmail());
       if (usuarioExistente.isPresent()) {
           throw new EmailAlreadyExistsException("Ya existe un usuario con el email proporcionado.");
       }
        if (!usuarioDTO.getPassword().equals(usuarioDTO.getConfirmPassword())) {
             throw new PasswordMismatchException("Las contraseñas no coinciden.");
         }

         // Verificar si el email ya existe

        Usuario usuario= new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setUserName(usuarioDTO.getUsername());
        usuario.setPassword(bCryptPasswordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUsuarioRole(UsuarioRole.ROLE_USER);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return usuarioDTO(usuarioGuardado);
   }

   public UsuarioDTO usuarioDTO(Usuario usuario) {
    UsuarioDTO dto = new UsuarioDTO();
    dto.setNombre(usuario.getNombre());
    dto.setUsername(usuario.getUsername());
    dto.setEmail(usuario.getEmail());
    
    return dto;
}

}
