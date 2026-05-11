package pe.edu.upeu.repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import pe.edu.upeu.model.Cliente;
import java.util.List;
@JdbcRepository(dialect = Dialect.H2)
public interface ClienteRepository extends CrudRepository<Cliente, String> {
    List<Cliente> findByNombreContainsIgnoreCase(String nombre);
    List<Cliente> findByDniContains(String dni);
}
