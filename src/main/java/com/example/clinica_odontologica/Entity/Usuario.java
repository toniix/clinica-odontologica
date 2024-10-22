package com.example.clinica_odontologica.Entity;


import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column
   private String nombre;
   @Column
   private String userName;
   @Column
   private String password;
   @Column
   private String email;
   

   @Enumerated(EnumType.STRING)
   private UsuarioRole usuarioRole;

   

    public Usuario(String nombre, String userName, String password, String email, UsuarioRole usuarioRole) {
    this.nombre = nombre;
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.usuarioRole = usuarioRole;
}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(usuarioRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

   

}
