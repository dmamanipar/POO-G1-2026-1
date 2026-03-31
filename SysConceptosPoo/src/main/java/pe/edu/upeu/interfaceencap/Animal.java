package pe.edu.upeu.interfaceencap;

import java.util.Objects;
import java.util.function.Consumer;

public interface Animal<T> {
    public void emitirSonido();
    public void dormir();
}
