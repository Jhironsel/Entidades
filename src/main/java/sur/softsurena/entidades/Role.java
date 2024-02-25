package sur.softsurena.entidades;

import java.util.Objects;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Role {
    private final String roleName;
    private final String propietario;
    private final String nombreProcedimiento;
    private final String descripcion;
    private final int opcionPermiso;
    private final boolean conAdmin;

    @Override
    public String toString() {
        if(Objects.isNull(roleName)){
            return nombreProcedimiento;
        }else{
            if(roleName.equalsIgnoreCase("RDB$ADMIN")){
                return "ADMINISTRADOR";
            }else if(roleName.equalsIgnoreCase("ADMINISTRADOR")){
                return "RDB$ADMIN";
            }
        }
        return roleName.strip();
    }

}
