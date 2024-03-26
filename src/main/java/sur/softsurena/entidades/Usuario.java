package sur.softsurena.entidades;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.abstracta.Persona;

@Getter
@SuperBuilder
public class Usuario extends Persona {

    private final String clave;
    private final String descripcion;
    private final Boolean administrador;
    private final List<Role> roles;
    private final List<Etiqueta> etiquetas;
    private final String tags;

    @Override
    public String toString() {
        return super.getUser_name().strip();
    }

}
