package pe.edu.upeu.repository;

import pe.edu.upeu.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    List<Cliente> clientes=new ArrayList<>();
    //C=Create
    public void agregarCliente(Cliente c){
        clientes.add(c);
    }
    //R=Report
    public List<Cliente> listarClientes(){
        return clientes;
    }
    //U=Update
    public void actualizarCliente(Cliente c, int index){
        clientes.set(index, c);
    }
    //D=Delete
    public void eliminarCliente(int index){
        clientes.remove(index);
    }
    public void eliminarTodo(){
        clientes.clear();
    }

    public void datosPrederminados(){
        clientes.add(new Cliente("01", "Juan Mateo Apaza Quispe",
                "915782414", "mateo@gmail.com"));
        clientes.add(new Cliente("02", "Pedrito Apaza Apaza"));
    }

    public int getClientes(){
        return clientes.size();
    }
}
