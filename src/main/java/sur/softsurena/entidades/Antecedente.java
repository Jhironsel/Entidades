package sur.softsurena.entidades;

import sur.softsurena.abstracta.Persona;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Antecedente extends Persona {

    private final int id;
    private final int id_consulta;
    private final String descripcion;
    
    @Override
    public String toString() {
        return descripcion.trim();
    }
}
