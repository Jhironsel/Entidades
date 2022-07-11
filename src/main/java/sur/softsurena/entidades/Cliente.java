package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Cliente extends Persona {

    public final static String INSERT_CLIENTE
            = "SELECT p.V_ID "
            + "FROM SP_INSERT_CLIENTE_SB (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
            + "'I_FECHANACIMIENTO', 'I_ESTADO', 'I_ESTADO_CIVIL') p;";

}
