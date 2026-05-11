package pe.edu.upeu.service;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import pe.edu.upeu.model.Cliente;
import pe.edu.upeu.repository.ClienteRepository;
import java.util.List;
@Transactional
@Singleton
public class ClienteServiceImp implements ClienteService {
    private final ClienteRepository repo;
    public ClienteServiceImp(ClienteRepository repo) {
        this.repo = repo;
    }
    @Override
    public Cliente save(Cliente c) {
        return repo.save(c);
    }
    @Override
    public Cliente update(Cliente c) {
        return repo.update(c);
    }
    @Override
    public List<Cliente> findAll() {
        return repo.findAll();
    }
    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }
    @Override
    public boolean existsById(String id) {
        return repo.existsById(id);
    }
}
