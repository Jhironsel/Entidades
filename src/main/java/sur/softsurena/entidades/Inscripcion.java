package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Inscripcion {
    private final int id;
    private final int id_estudiante;
    private final int id_padre_tutor;
    private final int id_tanda;
    private final BigDecimal pago;
    private final Date fecha_inscripcion;
    private final String idUsuario;

    @Override
    public String toString() {
        return "Inscripcion{" + 
                    "id=" + id + 
                    ", id_estudiante=" + id_estudiante + 
                    ", id_padre_tutor=" + id_padre_tutor + 
                    ", id_tanda=" + id_tanda + 
                    ", pago=" + pago + 
                    ", fecha_inscripcion=" + fecha_inscripcion + 
                    ", idUsuario=" + idUsuario + 
                '}';
    }
}
