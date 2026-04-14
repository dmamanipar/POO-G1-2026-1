package pe.edu.upeu.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pe.edu.upeu.model.Cliente;
import pe.edu.upeu.service.ClienteServiceImp;
import pe.edu.upeu.service.ClienteServiceInter;

public class MainClienteController {

    private static ClienteServiceInter cs= ClienteServiceImp.getIntance();

    @FXML
    TableView<Cliente> tableRegCliente;

    ObservableList<Cliente> clientes;
    private TableColumn<Cliente, String> colIdDni, colNombre, colTelefono, colEmail;

    @FXML private TextField txtDni, txtNombre, txtTelefono, txtEmail;
    @FXML private Button btnGuardar, btnActualizar, btnLimpiar, btnEliminar;
    int index=-1;
    @FXML
    public void initialize(){
        definirColumnas();
        listar();
        agregarEventoSeleccion();
        btnActualizar.setOnAction(event->{
            guardar();
            index=-1;
            limpiar();
            listar();
        });
        btnGuardar.setOnAction(e->{
            guardar();
            index=-1;
            limpiar();
            listar();
        });
    }

    @FXML
    void eliminar(ActionEvent e){
        if(index!=-1){
            cs.delete(index);
            index=-1;
            limpiar();
            listar();
        }
    }

    void limpiar(){
        txtDni.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }

    void guardar(){
        Cliente c=new Cliente();
        c.setId(txtDni.getText());
        c.setNombre(txtNombre.getText());
        c.setTelefono(txtTelefono.getText());
        c.setEmail(txtEmail.getText());
        if(index==-1 && !c.getId().isEmpty()){
           cs.save(c);
        }else{
            cs.update(c, index);
        }
    }



    public void agregarEventoSeleccion(){
        tableRegCliente.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue)->{
                    if(newValue!=null){
                        index=tableRegCliente.getItems().indexOf(newValue);
                        txtDni.setText(newValue.getId());
                        txtNombre.setText(newValue.getNombre());
                        txtEmail.setText(newValue.getEmail());
                        txtTelefono.setText(newValue.getTelefono());
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
        colIdDni.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getId()));

        colNombre.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getNombre()));
        colTelefono.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getTelefono()));
        colEmail.setCellValueFactory(cetCell->new SimpleStringProperty(cetCell.getValue().getEmail()));
        clientes= FXCollections.observableArrayList(cs.findAll());
        tableRegCliente.setItems(clientes);
    }



}
