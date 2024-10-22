package com.example.clinica_odontologica.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "domicilios")
public class Domicilio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Calle no puede ser vacia")
    private String calle;

    @Column(length = 5)
    private int numero;

    @NotBlank(message = "Localidad no puede ser vacia")
    private String localidad;

    @NotBlank(message = "Provincia no puede ser vacia")
    private String provincia;

    
    @OneToOne(mappedBy = "domicilio", fetch = FetchType.LAZY)
    @JsonBackReference
    private Paciente paciente;


    public Domicilio(@NotBlank(message = "Calle no puede ser vacia") String calle, int numero,
            @NotBlank(message = "Localidad no puede ser vacia") String localidad,
            @NotBlank(message = "Provincia no puede ser vacia") String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    

}
