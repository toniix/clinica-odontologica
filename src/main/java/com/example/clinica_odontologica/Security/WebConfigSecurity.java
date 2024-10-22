package com.example.clinica_odontologica.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.clinica_odontologica.Service.UsuarioService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity //<-- activa la seguridad.
public class WebConfigSecurity {
    //esta clase deberia autenticar y autorizar
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler(); // Devuelve el handler personalizado
    }


    @Bean //el metodo que nos va a ayudar con la autenticacion
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                    .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())
                        )       
                .authorizeHttpRequests((authz)-> authz
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/paciente/**").hasRole("ADMIN")
                        .requestMatchers("/odontologo/**").hasRole("ADMIN")
                        .requestMatchers("/turno/").hasRole("ADMIN")
                        .requestMatchers("/turno/registrar").hasRole("USER")
                        .requestMatchers("/home").hasRole("ADMIN")
                        .requestMatchers("/turno/listar").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customAuthenticationSuccessHandler())
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(withDefaults());
        return http.build();

    }

}
