package sur.softsurena.entidades;

import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Consulta extends Paciente {
    private final int id;
    private final int id_paciente;
    private final int id_control_consulta;
    private final Date fecha;
    private final int turno;
    private final Boolean estado;
    private final String usuario;
}
