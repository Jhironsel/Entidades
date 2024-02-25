package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Municipio {


    private final int id;
    private final String nombre;
    private final int idProvincia;

    

    @Override
    public String toString() {
        return nombre;
    }

}
