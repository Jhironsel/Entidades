package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Codigo_Postal {

    private int id;
    private int idProvincia;
    private String localidad;
    private int codigo_postal;

    public static String SELECT
            = "SELECT r.ID, r.IDPROVINCIA, r.LOCALIDAD, r.CODIGO_POSTAL "
            + "FROM V_CODIGOS_POSTALES r"
            + "WHERE r.IDPROVINCIA = ?;";

    @Override
    public String toString() {
        return localidad;
    }
}
