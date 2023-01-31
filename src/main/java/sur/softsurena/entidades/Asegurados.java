package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Asegurados extends Personas{
    private final int id_ars;
    private final String no_nss;
    private final Boolean estado;

    @Override
    public String toString() {
        return no_nss;
    }
    
    
}
