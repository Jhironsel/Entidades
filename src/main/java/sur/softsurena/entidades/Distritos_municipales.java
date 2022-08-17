package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Distritos_municipales {

    public final static String SELECT
            = "SELECT r.ID, r.NOMBRE " 
            + "FROM V_DISTRITOS_MUNICIPALES r " 
            + "WHERE r.IDMUNICIPIO = ? ";
    
    private final int id;
    private final String nombre;
    private final int idMunicipio;

    @Override
    public String toString() {
        return nombre;
    }
}