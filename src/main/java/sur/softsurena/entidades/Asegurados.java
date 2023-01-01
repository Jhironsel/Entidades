package sur.softsurena.entidades;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Asegurados extends Personas{
    @NonNull private final int id_ars;
    @NonNull private final String no_nss;
    @NonNull private final Boolean estado;

    @Override
    public String toString() {
        return no_nss;
    }
    
    
}
