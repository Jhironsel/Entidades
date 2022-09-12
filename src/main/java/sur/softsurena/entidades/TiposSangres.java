package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TiposSangres {

    private final int id;
    private final String descripcion;

    public static String SELECT
            = "SELECT ID, DESCRIPCION "
            + "FROM V_TIPOS_SANGRE order by 1";

    @Override
    public String toString() {
        return descripcion;
    }

}
