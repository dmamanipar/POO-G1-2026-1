package pe.edu.upeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import pe.edu.upeu.component.validation.DniUnic;

@Builder
@Entity(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cliente {
    @Id
    @DniUnic(message = "El DNI debe ser unico")
    @Column(name = "dni")
    @Size(min = 8, max = 8, message = "El DNI debe contener 8 digitos")
    @NotBlank(message = "El campo no puede ser vacio o nulo")
    private String dni;

    @NonNull
    @NotEmpty(message = "El nombre no puede vacio")
    private String nombre;

    @Pattern(regexp = "^9\\d{8}$", message = "El telefono debe cumplir un formato valido")
    @NotBlank(message = "El campo no puede ser vacio o nulo")
    private String telefono;

    @NotBlank(message = "El campo no puede ser vacio o nulo")
    @Email(message = "El correo no cumple con el formato")
    private String email;
}
