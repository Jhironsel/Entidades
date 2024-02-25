package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TipoPersona {

    private final String persona;
    private final char abreviatura;

    @Override
    public String toString() {
        return persona;
    }
}
