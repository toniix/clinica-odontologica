package com.example.clinica_odontologica.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private String nombre;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
