package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Sexo {
    private final char abreviatura;
    private final String sexo;

    @Override
    public String toString() {
        return sexo;
    }
}
