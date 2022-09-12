package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Municipios {

    public static String SELECT
            = "SELECT ID, NOMBRE "
            + "FROM V_MUNICIPIOS "
            + "WHERE IDPROVINCIA = ?";
    
    private final int id;
    private final String nombre;
    private final int idProvincia;

    

    @Override
    public String toString() {
        return nombre;
    }
    
    
}