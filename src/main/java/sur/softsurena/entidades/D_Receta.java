package sur.softsurena.entidades;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class D_Receta {
    
    private final int id_receta;
    private final int linea;
    private final int id_Medicamento;
    private final BigDecimal cantidad;
    private final String detalleDosis;
    
    @Override
    public String toString() {
        return "D_Recetas{" + 
                " id_receta=" + id_receta + 
                ", linea=" + linea + 
                ", id_Medicamento=" + id_Medicamento + 
                ", cantidad=" + cantidad + 
                ", detalleDosis=" + detalleDosis + 
                '}';
    }
    
}
