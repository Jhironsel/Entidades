package sur.softsurena.entidades;

import sur.softsurena.abstracta.Persona;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Generales extends Persona{
    
    private final String cedula;
    private final Integer id_tipo_sangre;
    private final Character estado_civil;
}
