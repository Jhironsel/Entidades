package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Motivo_Consulta {
    private final int id;
    private final String descripcion;
}
