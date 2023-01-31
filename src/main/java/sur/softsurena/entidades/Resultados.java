package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Resultados {
    private final int id;
    private final String mensaje;
    private final Integer cantidad;

    @Override
    public String toString() {
        return mensaje;
    }
    
    
}
