package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.abstracta.Persona;

@Getter
@SuperBuilder
public class Paciente extends Persona {

    @Override
    public String toString() {
        return super.toString();
    }
}
    