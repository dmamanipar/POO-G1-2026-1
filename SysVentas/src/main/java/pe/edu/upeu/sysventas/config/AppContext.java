package pe.edu.upeu.sysventas.config;

//import pe.edu.upeu.sysventas.controller.*;
import pe.edu.upeu.sysventas.utils.ConsultaDNI;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Contenedor de dependencias manual (Service Locator).
 * Reemplaza el ApplicationContext de Micronaut.
 *
 * Todas las instancias son Singletons inicializados una vez.
 * Los controladores JavaFX obtienen sus dependencias vía getBean(Class).
 */
public class AppContext {

    private static AppContext instance;
    private final Map<Class<?>, Object> beans = new HashMap<>();

    private AppContext() {
        registrarBeans();
    }

    public static synchronized AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    private void registrarBeans() {
        // ── Repositorios ────────────────────────────────────────────
        //CategoriaRepository     catRepo = new CategoriaRepository();


        //register(CategoriaRepository.class,    catRepo);


        // ── DataSource (de HikariCP a través de Hibernate) ──────────
        DataSource ds = DatabaseConfig.getDataSource();

        // ── Servicios ───────────────────────────────────────────────
        //ICategoriaService    catSvc = new CategoriaServiceImp(catRepo);

        ConsultaDNI          cDni   = new ConsultaDNI();

        //register(ICategoriaService.class,    catSvc);

        register(ConsultaDNI.class,          cDni);

        // ── Controladores JavaFX ─────────────────────────────────────
        // Registrados aquí para que FXMLLoader pueda resolverlos.
        //register(LoginController.class, new LoginController(usrSvc));

    }

    private void register(Class<?> type, Object bean) {
        beans.put(type, bean);
    }

    /**
     * Obtiene un bean por tipo (interfaz o clase concreta).
     * Equivalente a ApplicationContext.getBean(Class) de Micronaut/Spring.
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> type) {
        Object bean = beans.get(type);
        if (bean == null) {
            // Búsqueda por compatibilidad (subclase / implementación)
            bean = beans.values().stream()
                    .filter(b -> type.isAssignableFrom(b.getClass()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Bean no encontrado para: " + type.getName()
                            + ". Verifica que esté registrado en AppContext."));
        }
        return (T) bean;
    }
}
