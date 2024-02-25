package sur.softsurena.entidades;

import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Direccion {
    private final Integer id;
    private final Integer id_persona;
    private final Provincia provincia;
    private final Municipio municipio;
    private final Distrito_municipal distrito_municipal;
    private final Codigo_Postal codigo_postal;
    private final String direccion;
    private final Date fecha;
    private final Boolean estado;
    private final Boolean por_defecto;
    private final Character accion;
   

    @Override
    public String toString() {
        return direccion;
    }
}