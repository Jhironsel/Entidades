package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Padres extends Personas{

    private final Asegurados asegurado;
    private final Direcciones direccion;
    private final Generales generales;
}
