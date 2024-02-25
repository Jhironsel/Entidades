package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Codigo_Postal {
    private final int id;
    private final int idProvincia;
    private final String localidad;
    private final int codigo_postal;

    @Override
    public String toString() {
        return localidad;
    }
}
