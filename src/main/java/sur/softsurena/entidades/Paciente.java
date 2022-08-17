package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public class Paciente extends Personas{
    private final int idPadre;
    private final int idMadre;
    
    @Override
    public String toString() {
        return super.toString();
    }
}
