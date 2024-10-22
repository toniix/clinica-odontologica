package com.example.clinica_odontologica.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre no puede ser vacío")
    @Size(min = 3, max = 20)
    private String nombre;

    @NotBlank(message = "Apellido no puede ser vacío")
    @Size(min = 3, max = 30)
    private String apellido;

    @NotBlank(message = "DNI no puede ser vacío")
    @Column(unique = true)
    @Size(min = 3, max = 9, message = "El DNI no puede tener más de 9 caracteres")
    private String dni;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    @JsonManagedReference
    private Domicilio domicilio;

    public Paciente(@NotBlank(message = "Nombre no puede ser vacío") @Size(min = 3, max = 20) String nombre,
            @NotBlank(message = "Apellido no puede ser vacío") @Size(min = 3, max = 30) String apellido,
            @NotBlank(message = "DNI no puede ser vacío") @Size(min = 3, max = 9, message = "El DNI no puede tener más de 9 caracteres") String dni,
            LocalDate fechaIngreso, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    
}
