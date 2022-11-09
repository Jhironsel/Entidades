package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Paciente extends Personas {

    private final int idPadre;
    private final int idMadre;

    public static String UPDATE
            = "EXECUTE PROCEDURE SP_UPDATE_PACIENTE (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    public static String GET_SEXO_BY_ID
            = "SELECT sexo "
            + "FROM V_PACIENTES "
            + "WHERE idPaciente = ?";

    public static String GET_PACIENTES
            = "SELECT r.ID, r.ID_MADRE, r.ID_PADRE, r.ID_ARS, r.NONSS, r.ID_TIPO_SANGRE,\n"
            + "     r.CEDULA, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.SEXO, r.FECHA_NACIMIENTO,\n"
            + "     r.FECHA_INGRESO, r.FECHA_HORA_ULTIMO_UPDATE, r.ESTADO, r.ID_USUARIO\n"
            + "FROM GET_PACIENTES r"
            + "WHERE IDPACIENTE = ?";

    @Override
    public String toString() {
        return super.toString();
    }
}
