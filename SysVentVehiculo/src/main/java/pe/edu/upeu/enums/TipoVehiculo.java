package pe.edu.upeu.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
public enum TipoVehiculo {
    SEDAN("Sedán","SE"),
    SUV("SUV", "SU"),
    CAMIONETA("Camioneta", "CA"),
    DEPORTIVO("Deportivo","DE"),
    ELECTRICO("Eléctrico", "EL");

    private final String nombre, iniciales;

    TipoVehiculo(String nombre, String iniciales){
        this.nombre=nombre;
        this.iniciales=iniciales;
    }

    //Aplicando Polimorfismo
    @Override
    public String toString() {
        return  "{\"nombre\":\""+nombre+"\",\"iniciales\":\""+iniciales+"\"}";
        //return nombre+"\t"+iniciales;
    }
}
