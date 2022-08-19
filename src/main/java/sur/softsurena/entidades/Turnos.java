package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Turnos {

    public static String SELECT_IDUSUARIO_ESTADO
            = "SELECT id "
            + "FROM v_turnos "
            + "WHERE TRIM(idUsuario) like ? and estado";
    
    private final int id;
    private final Date fecha_inicio;
    private final Time hora_inicio;
    private final Date fecha_final;
    private final Time hora_final;
    private final Boolean estado;
    private final String idUsuario;
    


}
