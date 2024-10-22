package com.example.clinica_odontologica.Entity;






import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, length = 6)
    private Integer numeroMatricula;

    @NotBlank(message = "Nombre no puede ser vacío")
    @Size(min = 3, max = 20)
    private String nombre;

    @NotBlank(message = "Apellido no puede ser vacío")
    @Size(min = 3, max = 30)
    private String apellido;

    public Odontologo(Integer numeroMatricula,
            @NotBlank(message = "Nombre no puede ser vacío") @Size(min = 3, max = 20) String nombre,
            @NotBlank(message = "Apellido no puede ser vacío") @Size(min = 3, max = 30) String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    
    
}
