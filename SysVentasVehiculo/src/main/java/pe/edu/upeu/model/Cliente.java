package pe.edu.upeu.model;


import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.model.DataType;
import lombok.*;

@MappedEntity(value = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cliente {
    @Id
    @MappedProperty(value = "dni", type = DataType.STRING)
    private String dni;

    @MappedProperty(value = "nombre", type = DataType.STRING)
    private String nombre;

    @MappedProperty(value = "telefono", type = DataType.STRING)
    private String telefono;

    @MappedProperty(value = "email", type = DataType.STRING)
    private String email;

}
