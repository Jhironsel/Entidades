package sur.softsurena.entidades;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TipoPersona {

    private final String persona;
    private final char abreviatura;

    public static List<TipoPersona> getTipoPersonaList() {
        List<TipoPersona> tipoPersonaList = new ArrayList<>();

        tipoPersonaList.add(TipoPersona.builder().
                abreviatura('X').persona("Tipo de persona").build());
        tipoPersonaList.add(TipoPersona.builder().
                abreviatura('F').persona("FÍSICA").build());
        tipoPersonaList.add(TipoPersona.builder().
                abreviatura('J').persona("JURÍDICA").build());
        
        return tipoPersonaList;
    }

    @Override
    public String toString() {
        return persona;
    }

}
