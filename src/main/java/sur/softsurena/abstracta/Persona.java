package sur.softsurena.abstracta;

import java.sql.Date;
import java.util.Objects;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import sur.softsurena.entidades.Generales;

@Getter
@SuperBuilder
public class Persona {

    private final Integer id_persona;
    private final Character persona;
    private final String pnombre;
    private final String snombre;
    private final String apellidos;
    private final Character sexo;
    private final Date fecha_nacimiento;
    private final Date fecha_ingreso;
    private final Date fecha_hora_ultima_update;
    private final Boolean estado;
    private final String user_name;
    private final String rol;
    private final Generales generales;

        
    @Override
    public String toString() {
        StringBuilder nombre = new StringBuilder();
        nombre.append(pnombre).
                append(" ").
                append((snombre.isBlank() ? "" : snombre)).
                append(" ").
                append(apellidos);
        return nombre.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id_persona);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        
        if (!Objects.equals(this.id_persona, other.id_persona)) {
            return false;
        }
        
        return true;
    }
}
