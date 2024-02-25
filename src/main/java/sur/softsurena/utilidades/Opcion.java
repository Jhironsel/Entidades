package sur.softsurena.utilidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Opcion {

    private final Integer id;
    private final String valor;
    private final String descripcion;

    @Override
    public String toString() {
        return descripcion;
    }

}
