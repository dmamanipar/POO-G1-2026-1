package pe.edu.upeu.asociacion;

public class Profesor {

    public void ensenhar(Estudiante estudiante){
        System.out.println("Enseñar a "+estudiante.getNombre()+"");
    }

    public static void main(String[] args) {
        Profesor profesor = new Profesor();
        Estudiante estudiante = new Estudiante("Juan");
        profesor.ensenhar(estudiante);
    }
}
