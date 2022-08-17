package sur.softsurena.entidades;

import java.sql.Date;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Antecedentes extends Personas{

    public static String DELETE 
            ="delete from V_ANTECEDENTES WHERE ID = ?";
    
    private int id;
    @NonNull private int idDoctor;
    @NonNull private Date fecha;
    @NonNull private String descripcion;
    private String UserName;
    private String Rol;

    @Override
    public String toString() {
        return descripcion.trim();
    }
}
