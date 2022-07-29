package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Antecedente {

    public static String DELETE 
            ="delete from V_ANTECEDENTES WHERE ID = ?";
    
    private int idAntecedente;
    private int idPaciente;
    private String descripcion;

    @Override
    public String toString() {
        return descripcion.trim();
    }
    
    
}
