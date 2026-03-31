package pe.edu.upeu.enums;


enum GENERO{Masculino, Femenino}
public class Principal {

    public static void main(String[] args) {
        System.out.println(GENERO.Femenino);

        for (GENERO a:GENERO.values()){
            System.out.println(a);
        }

    }
}
