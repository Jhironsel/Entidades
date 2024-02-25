package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class EstadoCivil {

    private final Character abreviatura;
    private final String estadoCivil;

    @Override
    public String toString() {
        return estadoCivil;
    }

}
