package sur.softsurena.entidades;

import java.sql.Date;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class Personas {

    private final int id_persona;
    private final char persona;
    private final String pNombre;
    private final String sNombre;
    private final String apellidos;
    private final char sexo;
    private final Date fecha_nacimiento;
    private final Date fecha_ingreso;
    private final Date fecha_hora_ultima_update;
    private final Boolean estado;
    private final String user_name;
    private final String rol;

    private final Generales generales;

    private final Asegurados asegurado;

    private final List<Direcciones> direccion;
    private final List<ContactosEmail> contactosEmail;
    private final List<ContactosTel> contactosTel;

        
    @Override
    public String toString() {
        StringBuilder nombre = new StringBuilder();
        nombre.append(pNombre).
                append(" ").
                append((sNombre.isBlank() ? "" : sNombre)).
                append(" ").
                append(apellidos);
        return nombre.toString();
    }
}
