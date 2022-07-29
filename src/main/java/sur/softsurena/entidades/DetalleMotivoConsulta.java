package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class DetalleMotivoConsulta {

    public static String DELETE
            =
            "DELETE FROM D_MOTIVO_CONSULTA a " +
            "WHERE " +
            "     a.IDCONSULTA = ? AND " +
            "     a.TURNO = ? AND " +
            "     a.IDMCONSULTA = '?'";
    private final int idConsulta;
    private final int turno;
    private final int idMotivoConsulta;
    
    
}
