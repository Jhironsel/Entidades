package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Permiso {

    private final String nombre;
    private final String subfijo;

    @Override
    public String toString() {
        return nombre.strip();
    }

}
