package pe.edu.upeu.component.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pe.edu.upeu.service.ClienteService;
public class DniUnicoValidator implements ConstraintValidator<DniUnic, String> {
    private ClienteService service;
    public void initialize(ClienteService service) {
       this.service=service;
    }
    @Override
    public boolean isValid(String dni, ConstraintValidatorContext context) {
        if(service==null || dni==null || dni.isBlank()) return true;
        return !service.existsById(dni);
    }
}
