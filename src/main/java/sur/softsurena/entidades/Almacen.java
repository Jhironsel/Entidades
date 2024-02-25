package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Almacen {
    private final Integer id;
    private final String nombre;
    private final String ubicacion;
    private final Boolean estado;

    @Override
    public String toString() {
        return nombre;
    }
}
