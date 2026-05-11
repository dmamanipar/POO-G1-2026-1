package pe.edu.upeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Entity(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cliente {
    @Id
    @Column(name = "dni")
    @Size(min = 8, max = 8, message = "El DNI debe contener 8 digitos")
    @NotBlank(message = "El campo no puede ser vacio o nulo")
    private String dni;

    @NonNull
    @NotEmpty(message = "El nombre no puede vacio")
    private String nombre;

    private String telefono;

    @Email(message = "El correo no cumple con el formato")
    private String email;
}
