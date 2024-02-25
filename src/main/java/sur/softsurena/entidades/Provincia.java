package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Provincia {
    private final int id;
    private final String nombre;
    private final String zona;

    @Override
    public String toString() {
        return nombre;
    }
}
