package sur.softsurena.entidades;

import sur.softsurena.abstracta.Persona;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Paciente extends Persona {
    private final int idPadre;
    private final int idMadre;

    @Override
    public String toString() {
        return super.toString();
    }
}
