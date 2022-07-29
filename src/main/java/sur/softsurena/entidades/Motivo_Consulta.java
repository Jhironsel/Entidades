package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Motivo_Consulta {

    public static String DELETE
            ="DELETE FROM V_Motivos_Consulta WHERE ID = ?";
    
    private final int id;
    private final String descripcion;
    
}
