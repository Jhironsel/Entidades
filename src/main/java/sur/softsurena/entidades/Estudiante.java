package sur.softsurena.entidades;

import sur.softsurena.abstracta.Persona;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Estudiante extends Persona {

    private final String matricula;
    private final Integer idPadre;
    private final Integer idMadre;
    private final Integer idTutor;
    private final Integer jcb_parentesco;

    @Override
    public String toString() {
        return super.toString();
    }

}
