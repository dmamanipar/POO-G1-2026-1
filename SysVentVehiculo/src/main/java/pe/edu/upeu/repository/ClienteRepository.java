package pe.edu.upeu.repository;

import pe.edu.upeu.conn.SQLiteConnection;
import pe.edu.upeu.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    Connection conn= SQLiteConnection.getInstance().getConnection();
    Statement st;
    ResultSet rs;

    private static ClienteRepository intance=new ClienteRepository();

    public static ClienteRepository getInstance(){
        if(intance==null){
            intance=new ClienteRepository();
        }
        return intance;
    }

    List<Cliente> clientes=new ArrayList<>();
    //C=Create
    public void agregarCliente(Cliente c){
        String sql = "INSERT INTO cliente (id, nombre, telefono, email) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //R=Report
    public List<Cliente> listarClientes(){
        try {
            st=conn.createStatement();
            rs=st.executeQuery("select * from cliente");
            while (rs.next()){
                Cliente c=new Cliente();
                c.setId(rs.getString("id"));
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
                clientes.add(c);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return clientes;
    }
    //U=Update
    public void actualizarCliente(Cliente c, int index){
        clientes.set(index, c);
    }
    //D=Delete
    public void eliminarCliente(String index){
        String sql = "DELETE FROM cliente WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, index);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
