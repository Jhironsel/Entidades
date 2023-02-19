package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Roles {
    private final String roleName;
    private final String propietario;
    private final String descripcion;
}