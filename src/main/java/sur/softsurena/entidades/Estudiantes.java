package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Estudiantes extends Personas {

    /**
     * Procedimiento almacenado que permite registro de estudiantes al sistema.
     * 
     * Los campos necesarios son: 
     * 'I_ID_ARS', 
     * 'I_NO_NSS', 
     * 'I_ID_TIPO_SANGRE', 
     * 'I_CEDULA', 
     * 'I_PNOMBRE', 
     * 'I_SNOMBRE', 
     * 'I_APELLIDOS', 
     * 'I_SEXO', 
     * 'I_FECHANACIMIENTO', 
     * 'I_ESTADO', 
     * 'I_MATRICULA', 
     * 'I_ID_PADRE', 
     * 'I_ID_MADRE', 
     * 'I_ID_TUTOR', 
     * 'I_JCB_PARENTESCO'
     */
    public static String INSERT_ESTUDIANTE
            ="EXECUTE PROCEDURE SP_INSERT_ESTUDIANTE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    
    private final String matricula;
    private final Integer idPadre;
    private final Integer idMadre;
    private final Integer idTutor;
    private final Integer jcb_parentesco;

    @Override
    public String toString() {
        return super.toString();
    }
    
}
