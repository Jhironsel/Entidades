package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class EstadoCivil {
    private char abreviatura;
    private String estadoCivil;

    @Override
    public String toString() {
        return estadoCivil;
    }

}
