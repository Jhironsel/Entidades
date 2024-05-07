package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.abstracta.Persona;

@SuperBuilder
@Getter
public class Generales extends Persona{
    
    private final String cedula;
    private final Integer id_tipo_sangre;
    private final Character estado_civil;

    @Override
    public String toString() {
        return cedula;
    }
    
    
}
