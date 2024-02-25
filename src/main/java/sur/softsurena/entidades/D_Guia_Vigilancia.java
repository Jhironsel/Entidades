package sur.softsurena.entidades;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class D_Guia_Vigilancia {

    private final int id;
    private final int id_paciente;
    private final Timestamp fecha;

    @Override
    public String toString() {
        return "D_guia_vigilancia{" + 
                " id=" + id + 
                ", id_paciente=" + id_paciente + 
                ", fecha=" + fecha + 
                '}';
    }
}
