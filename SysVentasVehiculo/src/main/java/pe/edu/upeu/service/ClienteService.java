package pe.edu.upeu.service;
import pe.edu.upeu.model.Cliente;
import java.util.List;
public interface ClienteService {
    Cliente save(Cliente c);
    Cliente update(Cliente c);
    List<Cliente> findAll();
    void delete(String id);
    boolean existsById(String id);
}
