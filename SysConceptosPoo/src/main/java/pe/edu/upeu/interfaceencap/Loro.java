package pe.edu.upeu.interfaceencap;

public class Loro implements Animal{

    @Override
    public void emitirSonido() {
        System.out.println("Hola manito....aprende pues!");
    }

    @Override
    public void dormir() {
        System.out.println("Zzz...zzz..zz");
    }

    public void cantar(){
        System.out.println("Lalalalala....");
    }
}
