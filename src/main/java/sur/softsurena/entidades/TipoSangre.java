package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TipoSangre {
    private final int id;
    private final String descripcion;

    @Override
    public String toString() {
        return descripcion;
    }
}
