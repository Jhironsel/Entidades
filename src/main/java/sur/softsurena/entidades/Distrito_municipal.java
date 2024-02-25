package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Distrito_municipal {

    private final Integer id;
    private final String nombre;
    private final Integer idMunicipio;

    @Override
    public String toString() {
        return nombre;
    }
}
