package pe.edu.upeu.interfaceencap;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    List a=new ArrayList();

    public static void main(String[] args) {
        Animal a=new Loro();
        a.emitirSonido();
        a.dormir();

    }
}
