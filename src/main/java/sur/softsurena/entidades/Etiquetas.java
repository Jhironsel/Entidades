package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Etiquetas {
    private final String propiedad;
    private final String valor;

    @Override
    public String toString() {
        return  valor;
    }
    
    
}
