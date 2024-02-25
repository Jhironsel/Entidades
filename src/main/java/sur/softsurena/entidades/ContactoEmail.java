package sur.softsurena.entidades;

import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ContactoEmail {
    
    private final Integer id;
    private final Integer id_persona;
    //La accion podrá ser i Insertar, a actualizar o b borrar
    private final Character accion;
    private final String email;
    private final Date fecha;

    @Override
    public String toString() {
        return email;
    }

}
