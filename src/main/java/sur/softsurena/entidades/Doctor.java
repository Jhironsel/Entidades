package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Doctor extends Personas{
    private final String especialidad;
    private final String execuatur;
}
