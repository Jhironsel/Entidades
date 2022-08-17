package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Generales extends Personas{
    
    private final String cedula;
    private final int id_tipo_sangre;
    private final char estado_civil;
    
}
