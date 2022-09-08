package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Provincias {
    private final int id;
//    private final int id_provincias;
    private final String nombre;
    private final String zona;
    
    public final static String SELECT
            = "SELECT ID, NOMBRE FROM V_PROVINCIAS r";

    @Override
    public String toString() {
        return nombre;
    }    
}
