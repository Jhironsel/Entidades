package sur.softsurena.entidades;

import java.sql.Time;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Control_Consulta {

    private final int id;
    private final String user_name;
    private final int cantidad;
    private final String dia;
    private final Time inicial;
    private final Time finall;
    private final Boolean estado;
    
    @Override
    public String toString() {
        return user_name;
    }
}
