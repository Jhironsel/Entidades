package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.abstracta.Persona;

@Getter
@SuperBuilder
public class Estudiante extends Persona {

    private final String matricula;
    private final Integer jcb_parentesco;

    @Override
    public String toString() {
        return super.toString();
    }

}
