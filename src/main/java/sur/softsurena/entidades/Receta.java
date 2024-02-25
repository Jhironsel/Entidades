package sur.softsurena.entidades;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Receta {

    private final int id;
    private final int idConsulta;
    private final Timestamp fecha;
    private final String usuario;

    @Override
    public String toString() {
        return "Recetas{" + "id=" + id
                + ", idConsulta=" + idConsulta
                + ", fecha=" + fecha 
                + ", usuario=" + usuario 
                + '}';
    }

}
