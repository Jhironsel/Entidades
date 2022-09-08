package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Paciente extends Personas {

    private final int idPadre;
    private final int idMadre;

    public static String GET_SEXO_BY_ID
            = "SELECT sexo "
            + "FROM V_PACIENTES "
            + "WHERE idPaciente = ?";
    
    public static String GET_PACIENTES
            = "SELECT NOMBRES, APELLIDOS, IDARS, NONSS "
            + "FROM GET_PACIENTES "
            + "WHERE IDPACIENTE = ?";

    @Override
    public String toString() {
        return super.toString();
    }
}
