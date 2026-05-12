package pe.edu.upeu.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pe.edu.upeu.component.ToltipCustom;
import pe.edu.upeu.component.ValidadorFormulario;
import pe.edu.upeu.component.validation.DniUnicoValidator;
import pe.edu.upeu.model.Cliente;
import pe.edu.upeu.service.ClienteService;

import java.util.Map;
import java.util.Set;

@Singleton
public class MainClienteController {
    @Inject
    ClienteService cs;

    @FXML
    TableView<Cliente> tableRegCliente;

    ObservableList<Cliente> clientes;
    private TableColumn<Cliente, String> colIdDni, colNombre, colTelefono, colEmail;

    @FXML private TextField txtDni, txtNombre, txtTelefono, txtEmail, txtBuscar;
    @FXML private Button btnGuardar, btnActualizar, btnLimpiar, btnEliminar, btnListar;
    int index=-1;
    String dni="";

    FilteredList<Cliente> filteredData;
    private ToltipCustom ttc =new ToltipCustom();
    private ValidadorFormulario<Cliente> validador;
    private Map<String, TextField> camposUI;

    @FXML
    public void initialize(){
        definirColumnas();
        listar();
        botonDesactivar(true);
        agregarEventoSeleccion();

        validador=new ValidadorFormulario<>(initValidator(cs), ttc);
        camposUI=Map.of(
                "dni", txtDni,
                "nombre", txtNombre,
                "telefono", txtTelefono,
                "email", txtEmail
        );

        btnActualizar.setOnAction(event->{
            if(guardar(true)){
            index=-1;
            limpiar();
            listar();
            botonDesactivar(true);
            btnGuardar.setDisable(false);
            }
        });
        btnGuardar.setOnAction(e->{
            if(guardar(false)){
            index=-1;
            limpiar();
            listar();
            }
        });
        btnLimpiar.setOnAction(e->{
            limpiar();
            index=-1;
            botonDesactivar(true);
            btnGuardar.setDisable(false);
        });
        btnListar.setOnAction(e->{
            listar();
        });

        filtrarDatos();
    }

    @FXML
    public void buscarPorDniNombre(ActionEvent e){
        System.out.println(txtBuscar.getText());
        colIdDni.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getDni()));

        colNombre.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getNombre()));
        colTelefono.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getTelefono()));
        colEmail.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getEmail()));
        clientes= FXCollections.observableArrayList(cs.buscarDniNombre(txtBuscar.getText()));
        tableRegCliente.setItems(clientes);
    }

    private Validator initValidator(ClienteService csx){
        Configuration<?> config= Validation.byDefaultProvider().configure();
        config.constraintValidatorFactory(new ConstraintValidatorFactory() {
            @Override
            public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
               if(key== DniUnicoValidator.class){
                   DniUnicoValidator v=new DniUnicoValidator();
                   v.initialize(csx);
                   return key.cast(v);
               }

               try {
                   return key.getDeclaredConstructor().newInstance();
               } catch (Exception e) {
                   throw new RuntimeException("No se pudo instanciar:"+key, e);
               }
            }

            @Override
            public void releaseInstance(ConstraintValidator<?, ?> instance) {
            }
        } );
        return config.buildValidatorFactory().getValidator();
    }

    void filtrarDatos(){
        filteredData=filteredData = new FilteredList<>(clientes, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getDni().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Cliente> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableRegCliente.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        tableRegCliente.setItems(sortedData);
    }

    void botonDesactivar(boolean estado){
        btnActualizar.setDisable(estado);
        btnEliminar.setDisable(estado);
    }

    @FXML
    void eliminar(ActionEvent e){
        if(!dni.isEmpty()){
            cs.delete(dni);
            index=-1;
            limpiar();
            listar();
            botonDesactivar(true);
            btnGuardar.setDisable(false);
        }
    }

    void limpiar(){
        txtDni.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        tableRegCliente.getSelectionModel().clearSelection();
        camposUI.values().forEach(ttc::limpiarCampo);
    }

    boolean guardar(boolean esActualizar){
        Cliente c=new Cliente();
        c.setDni(txtDni.getText());
        c.setNombre(txtNombre.getText());
        c.setTelefono(txtTelefono.getText());
        c.setEmail(txtEmail.getText());

        Set<String> excluir=esActualizar?Set.of("DniUnic"):Set.of();
        if(!validador.validar(c, camposUI, excluir)) return false;

        if(index==-1 && !c.getDni().isEmpty()){
           cs.save(c);
        }else{
            if(index==-1){
                System.out.println("sdsdsd");
                Alert a=new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.show();
            }else{
                c.setDni(dni);
                cs.update(c);
            }
        }
        return true;
    }
    public void agregarEventoSeleccion(){
        tableRegCliente.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue)->{
                    if(newValue!=null){
                        index=tableRegCliente.getItems().indexOf(newValue);
                        dni=newValue.getDni();
                        txtDni.setText(newValue.getDni());
                        txtNombre.setText(newValue.getNombre());
                        txtEmail.setText(newValue.getEmail());
                        txtTelefono.setText(newValue.getTelefono());
                        botonDesactivar(false);
                        btnGuardar.setDisable(true);
                    }
                });
    }

    public void definirColumnas(){
        colIdDni=new TableColumn<>("DNI");
        colNombre=new TableColumn<>("Nombre");
        colTelefono=new TableColumn<>("Telefono");
        colEmail=new TableColumn<>("Email");
        tableRegCliente.getColumns().addAll(colIdDni, colNombre, colTelefono, colEmail);
    }

    public void listar(){
        colIdDni.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getDni()));

        colNombre.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getNombre()));
        colTelefono.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getTelefono()));
        colEmail.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getEmail()));
        clientes= FXCollections.observableArrayList(cs.findAll());
        tableRegCliente.setItems(clientes);
    }



}
