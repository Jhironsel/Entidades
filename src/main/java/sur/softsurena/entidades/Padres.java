package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Padres extends Personas {

    public static String RECUPERAR_PADRE
            = "SELECT NOMBRES, APELLIDOS, SEXO, IDTIPOSANGRE, IDARS, "
            + "NONSS, TELEFONO, MOVIL, CORREO, DIRECCION, CIUDAD, "
            + "FECHANACIMIENTO "
            + "FROM V_PADRES "
            + "WHERE CEDULA = ? AND ESTADO IS FALSE;";
    
    
    public static String CAMBIAR_ESTADO
            ="UPDATE V_Padres "
                    + "SET "
                    + "    ESTADO = FALSE "
                    + "WHERE "
                    + "    CEDULA = ?";

}
