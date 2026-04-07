package pe.edu.upeu;

import pe.edu.upeu.enums.TipoVehiculo;
import pe.edu.upeu.model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println(TipoVehiculo.SEDAN.toString());

        for (TipoVehiculo tv:TipoVehiculo.values()){
            System.out.println(tv.toString());
        }

        List<Cliente> clientes=new ArrayList<>();
        clientes.add(new Cliente("01", "David Mamani",
                "951782520", "c01@gmail.com"));

        clientes.add(new Cliente("02", "Raul Gomez Apaza",
                "951782521", "c02@gmail.com"));

        clientes.add(new Cliente("03", "Pedro Gonzales Torres",
                "951782522", "c03@gmail.com"));

        clientes.add(new Cliente("04", "Alex Raul Peredo Paredes",
                "951782523", "c04@gmail.com"));

        for (Cliente c: clientes){
            System.out.println(c.toString());
        }

    }
}
